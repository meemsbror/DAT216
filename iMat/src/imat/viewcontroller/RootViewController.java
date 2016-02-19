package imat.viewcontroller;

import imat.viewcontroller.ContentViewController;
import imat.viewcontroller.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;

import java.util.List;


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

    @Override
    public void initialize() {
        // TODO: Perform initialization!
        setCategories();

    }

    private void setCategories(){
        ProductCategory pcs [] = ProductCategory.values();
        for(ProductCategory ps:pcs){
            categoryButton btn = new categoryButton(ps);
            btn.setOnAction((e) -> productCategorySelected(e));
            // TODO: Add button to view.
        }
    }

    public void setContent(ContentViewController c){
        this.content=c;
    }

    private void productCategorySelected(ActionEvent evt){
        //TODO: Choose category and show it.
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



    public void toHomePageActionPerformed(ActionEvent evt){
        //TODO
    }

    public void toAllContentActionPerformed(ActionEvent evt){
        //TODO
    }

    public void toFavoritesPerformed(ActionEvent evt){
        //TODO
    }

}
