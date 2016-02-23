package imat.viewcontroller;

import imat.formatting.ProductCategoryFormatter;
import imat.model.AllProductsProductFilter;
import imat.model.CategoryProductFilter;
import imat.model.FavouritesProductFilter;
import imat.model.SearchProductFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import se.chalmers.ait.dat215.project.ProductCategory;

import java.util.HashMap;
import java.util.Map;

public class RootViewController extends ViewController {

    @FXML private Button favoriteButton;
    @FXML private Button allContentButton;
    @FXML private Button homePageButton;
    @FXML private Button historyButton;
    @FXML private Button cartButton;
    @FXML private Button checkoutButton;
    @FXML private Button searchButton;
    @FXML private TextField searchTextField;



    @FXML private BorderPane borderPane;
    ContentViewController content;

    @FXML private VBox categoryVBox;
    private Button currentlySelectedCategoryButton;
    private Map<Button, ProductCategory> buttonCategoryMap = new HashMap<>();

    // Reuse this for every time a ListViewController is needed
    private ListViewController reuseListViewController = ListViewController.load("ListView.fxml");

    @Override
    public void initialize() {
        setCategories();
        toAllContentActionPerformed(new ActionEvent(allContentButton, null));
    }

    @Override
    protected void viewDidSet(Parent view) {

    }

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
        if (evt.getSource() instanceof Button) {
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
        //TODO
    }

    public void toCartButtonActionPerformed(ActionEvent evt){
        //TODO
    }

    public void toHistoryActionPerformed(ActionEvent evt){
        //TODO
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

}
