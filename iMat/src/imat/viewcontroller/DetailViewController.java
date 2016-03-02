package imat.viewcontroller;

import imat.filter.FavouritesProductFilter;
import imat.formatting.PriceFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;


public class DetailViewController extends ContentViewController {

    @FXML private ImageView productImage;
    @FXML private Text productName;
    @FXML private Text productPrice;
    @FXML private Button addToFavoriteButton;
    @FXML private Button removeFavoriteButton;
    @FXML private Button backButton;
    @FXML private Button addToCartButton;
    @FXML private TextField amountCalculator;
    @FXML private ToggleButton amountDown;
    @FXML private ToggleButton amountUp;
    @FXML private Text totalPrice;
    @FXML private Text inCartText;
    @FXML private Text feedBackText;

    private Product activeProduct;

    private ContentViewController sourceViewController;
    private String notInCart = "Produkten finns ej i kundvagnen";
    private String inCart = "Produkten finns nu i kundvagnen";




    @Override
    public void initialize() {

    }

    @Override
    protected void viewDidSet(Parent view) {
    }

    public void setProduct(Product p){
        if(indexInCart(p)>=0){
            ShoppingItem item = IMatDataHandler.getInstance().getShoppingCart().getItems().get(indexInCart(p));
            updateAmount(item);
        }
        this.activeProduct=p;
        setTitle();
        setPrice();
        setProductImage();
        setTotalPrice();
        setFeedBackText();
        if(IMatDataHandler.getInstance().isFavorite(activeProduct)){
            removeFavoriteButton.toFront();
        }else{
            addToFavoriteButton.toFront();
        }

    }


    public void setTitle() {
        productName.setText(activeProduct.getName());
    }

    public void setPrice(){
        productPrice.setText(String.valueOf(PriceFormatter.getFormattedPrice(activeProduct)));
    }

    public void setProductImage(){
        Image detailImage = IMatDataHandler.getInstance().getFXImage(activeProduct, 247, 214);
        productImage.setImage(detailImage);

    }
    public void setTotalPrice(){
        double totPrice = 0.0;

        int index = indexInCart(activeProduct);

        // If the product can't be found in the cart, the total price is obv. 0.
        if (index >= 0) {
            ShoppingItem theItem = IMatDataHandler.getInstance().getShoppingCart().getItems().get(index);
            totPrice = theItem.getAmount() * activeProduct.getPrice();
        }

        totalPrice.setText(String.valueOf(totPrice));
    }

    private void updateAmount(ShoppingItem item){
        inCartText.setText("Du har redan " + (item.getAmount() + " st i kundvagnen"));
    }

    public void setFeedBackText() {
        if (indexInCart(activeProduct) >= 0) {
            updateAmount(item);
        }
        else {
            feedBackText.setText(notInCart);
        }
    }


    public void addAndRemoveToFavorites(ActionEvent evt){
        if(evt.getSource().equals(addToFavoriteButton)) {
            IMatDataHandler.getInstance().addFavorite(activeProduct);
            this.removeFavoriteButton.toFront();
        }if(evt.getSource().equals(removeFavoriteButton)){
            IMatDataHandler.getInstance().removeFavorite(activeProduct);
            addToFavoriteButton.toFront();
        }

        if (sourceViewController instanceof ListViewController) {
            ListViewController lvc = (ListViewController) sourceViewController;
            if (lvc.getProductFilter() instanceof FavouritesProductFilter) {
                // If the source is a ListView with a FavouritesProductFilter, make sure it updates its list
                // since the addAndRemoveToFavourites method is bound to change its content.
                lvc.refetchProducts();
            }
        }

    }

    public void setSourceView(ContentViewController content){
        this.sourceViewController = content;
    }

    public void backToSource(ActionEvent evt){
        if(evt.getSource().equals(backButton)){
            RootViewController.getInstance().setContent(sourceViewController);
        }
    }


    public void addToCart(ActionEvent evt){
        if(evt.getSource().equals(addToCartButton)) {
            System.out.println(activeProduct.getName() + "Tillagd i kundvagn");
            double tmp;
            try {
                tmp = Double.parseDouble(amountCalculator.getText());
            } catch (NumberFormatException e1) {
                tmp = 1;
            }
            IMatDataHandler.getInstance().getShoppingCart().addProduct(activeProduct, tmp);
            updateAmount(IMatDataHandler.getInstance().getShoppingCart().getItems().get(indexInCart(activeProduct)));
            System.out.println(IMatDataHandler.getInstance().getShoppingCart().getItems().get(0).getProduct().getName());
            setFeedBackText();
        }
    }
}
