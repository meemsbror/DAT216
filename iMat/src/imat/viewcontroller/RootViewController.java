package imat.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public void initialize() {
        setCategories();
        toAllContentActionPerformed(new ActionEvent(allContentButton, null)); // FIXME: Hack...
    }

    private void setCategories() {
        for(ProductCategory productCategory : ProductCategory.values()) {

            // Create styled button
            Button btn = new Button(productCategory.name());
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
                // TODO: Set content appropriately.
            }
        }
    }

    public void toAllContentActionPerformed(ActionEvent evt){
        if (evt.getSource() instanceof Button) {
            Button button = (Button) evt.getSource();
            toggleSelectedCategoryButton(button);
        }
        // TODO: Set content appropriately.
    }

    public void toFavoritesPerformed(ActionEvent evt){
        if (evt.getSource() instanceof Button) {
            Button button = (Button) evt.getSource();
            toggleSelectedCategoryButton(button);
        }
        // TODO: Set content appropriately.
    }

    private void toggleSelectedCategoryButton(Button current) {
        current.getStyleClass().add("selected-category");
        if (currentlySelectedCategoryButton != null) {
            currentlySelectedCategoryButton.getStyleClass().remove("selected-category");
        }
        currentlySelectedCategoryButton = current;
    }

    public void toHomePageActionPerformed(ActionEvent evt) {
        // Delegate to "all content category selected"
        toAllContentActionPerformed(new ActionEvent(allContentButton, null)); // FIXME: Hack...
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

    public void toSearchActionPerformed(ActionEvent evt){
        //TODO
        String searchText = searchTextField.getText();
        List<Product> searchResults = IMatDataHandler.getInstance().findProducts(searchText);
        //ToDo setContent();


    }

    public void setContent(ContentViewController c){
        this.content = c;
    }

}
