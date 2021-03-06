package imat.viewcontroller;

import imat.filter.AllProductsProductFilter;
import imat.filter.CategoryProductFilter;
import imat.filter.FavouritesProductFilter;
import imat.filter.SearchProductFilter;
import imat.formatting.ProductCategoryFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import se.chalmers.ait.dat215.project.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RootViewController extends ViewController implements ShoppingCartListener{

    @FXML private Button favoriteButton;
    @FXML private Button allContentButton;
    @FXML private Button homePageButton;
    @FXML private Button historyButton;
    @FXML private Button cartButton;
    @FXML private Button checkoutButton;
    @FXML private Button searchButton;
    @FXML private TextField searchTextField;

    private static RootViewController instance;
    private HashMap<Product,Double> cart = new HashMap<>();
    private boolean hasRunCartSetup=false;




    @FXML private BorderPane borderPane;
    ContentViewController content;

    @FXML private VBox categoryVBox;
    private Button currentlySelectedCategoryButton;
    private Map<Button, ProductCategory> buttonCategoryMap = new HashMap<>();

    // Reuse these every time such a view is needed
    private ListViewController reuseListViewController = ListViewController.load("ListView.fxml");
    private DetailViewController reuseDetailViewController = DetailViewController.load("DetailView.fxml");
    private CartViewController reuseCartViewController = CartViewController.load("CartView.fxml");

    @Override
    public void initialize() {
        setCategories();
        toAllContentActionPerformed(new ActionEvent(allContentButton, null));
        IMatDataHandler.getInstance().getShoppingCart().addShoppingCartListener(this);
        addItemsOnStart();
    }



    @Override
    protected void viewDidSet(Parent view) {

    }

    //Sets the categorybuttons and adds them to the view.
    private void setCategories() {
        for(ProductCategory productCategory : ProductCategory.values()) {

            // Create styled button
            String formattedName = ProductCategoryFormatter.getFormattedName(productCategory);
            Button btn = new Button(formattedName);
            btn.getStyleClass().add("category-button");

            btn.setOnAction(this::productCategorySelected);
            buttonCategoryMap.put(btn, productCategory);

            categoryVBox.getChildren().add(btn);

            // Too tired to do this properly...
            btn.prefWidthProperty().bind(allContentButton.widthProperty().subtract(19));
        }
    }

    private void productCategorySelected(ActionEvent evt){
        if (evt.getSource() instanceof Button && !evt.getSource().equals(currentlySelectedCategoryButton)) {
            Button button = (Button)evt.getSource();

            toggleSelectedCategoryButton(button);

            ProductCategory category = buttonCategoryMap.get(button);
            if (category != null) {
                reuseListViewController.setProductFilter(new CategoryProductFilter(category));
                setContent(reuseListViewController);
            }
        }
    }

    public void toAllContentActionPerformed(ActionEvent evt){
        if (evt.getSource() instanceof Button) {
            Button button = (Button) evt.getSource();
            toggleSelectedCategoryButton(button);
            reuseListViewController.setProductFilter(new AllProductsProductFilter());
            setContent(reuseListViewController);
        }
    }

    public void toFavoritesPerformed(ActionEvent evt){
        if (evt.getSource() instanceof Button) {
            Button button = (Button) evt.getSource();
            toggleSelectedCategoryButton(button);

            reuseListViewController.setProductFilter(new FavouritesProductFilter());
            setContent(reuseListViewController);
        }
    }

    public void toSearchActionPerformed(ActionEvent evt){
        if (searchTextField.getText().trim().length() > 0) {
            toggleSelectedCategoryButton(searchButton);
            String searchText = searchTextField.getText();
            reuseListViewController.setProductFilter(new SearchProductFilter(searchText));

            setContent(reuseListViewController);
            for(ProductCategory productCategory : ProductCategory.values()){
                if(ProductCategoryFormatter.getFormattedName(productCategory).trim().toLowerCase().equals(searchText.toLowerCase())){
                    reuseListViewController.setProductFilter(new CategoryProductFilter(productCategory));
                    setContent(reuseListViewController);
                }
            }
        }
    }

    private void toggleSelectedCategoryButton(Button current) {
        if (current != null) {
            current.getStyleClass().add("selected-category");
        }
        if (currentlySelectedCategoryButton != null) {
            currentlySelectedCategoryButton.getStyleClass().remove("selected-category");
        }
        currentlySelectedCategoryButton = current;
    }

    public void toHomePageActionPerformed(ActionEvent evt) {
        // Delegate to "all content category selected" since they mean the exact same
        toAllContentActionPerformed(new ActionEvent(allContentButton, null));
    }

    public void toCheckoutActionPerformed(ActionEvent evt){
        if(evt.getSource().equals(checkoutButton) && !(content instanceof CheckOutViewController)){
            CheckOutViewController checkOutViewController = CheckOutViewController.load("CheckOutView.fxml");
            setContent(checkOutViewController);
            //checkOutViewController.showCart();
        }
    }

    public void toCartButtonActionPerformed(ActionEvent evt){
        if(evt.getSource().equals(cartButton) && !(content instanceof CartViewController)){
            CartViewController cartViewController = CartViewController.load("CartView.fxml");
            setContent(cartViewController);
            cartViewController.showCart();
        }
    }

    public void toHistoryActionPerformed(ActionEvent evt){

        if(evt.getSource().equals(historyButton) && !(content instanceof HistoryViewController)){
            HistoryViewController historyViewController = HistoryViewController.load("HistoryView.fxml");
            setContent(historyViewController);
            historyViewController.showHistory();

        }
    }

    /**
     *
     * Call this to set the content of the root view controller, i.e. the big center panel.
     *
     * @param c content to set
     */
    public void setContent(ContentViewController c){
        this.content = c;
        this.borderPane.setCenter(c.getView());
    }

    public static RootViewController getInstance(){
        if(instance == null){
            instance = RootViewController.load("RootView.fxml");
        }
        return instance;
    }

    public ListViewController getReuseListView() {
        return reuseListViewController;
    }

    public DetailViewController getReuseDetailViewController() {
        return reuseDetailViewController;
    }

    public Map<Product,Double> getCart(){
        return cart;
    }

    public void shoppingCartChanged(CartEvent event){
        if (event.getShoppingItem() != null) {
            if (event.isAddEvent()) {
                addItem(event.getShoppingItem());
            } else {
                removeItem(event.getShoppingItem());
            }
        }
    }

    private void addItem(ShoppingItem item){
        if(cart.containsKey(item.getProduct())) {
            incrementItem(item);
        }
        else {
            cart.put(item.getProduct(),item.getAmount());
        }
    }

    private void addItemsOnStart(){
        if(!hasRunCartSetup) {
            for (ShoppingItem item : IMatDataHandler.getInstance().getShoppingCart().getItems()) {
                addItem(item);
            }
            hasRunCartSetup=true;
            System.out.println(IMatDataHandler.getInstance().getShoppingCart().getItems().size());
        }
    }

    private void removeItem(ShoppingItem item){
        cart.remove(item.getProduct());
    }

    private void decrementItem(Product product, int i){
        double amount = cart.get(product);
        amount -= i;
        cart.put(product,amount);

    }

    private void incrementItem(ShoppingItem item){
        double amount = cart.get(item.getProduct());
        amount += item.getAmount();
        cart.put(item.getProduct(),amount);
    }

    public void removeCart(){
        cart.clear();
        IMatDataHandler.getInstance().getShoppingCart().clear();
    }


    public CartViewController getReuseCartViewController(){
        reuseCartViewController.showCart();
        return reuseCartViewController;
    }

    public double getTotalPrice(){
        double totalPrice = 0;

        for(Entry <Product,Double> entry:cart.entrySet()){
            double productPrice = entry.getKey().getPrice();
            double productAmount = entry.getValue();
            totalPrice += productPrice*productAmount;
        }
        return totalPrice;
    }
}
