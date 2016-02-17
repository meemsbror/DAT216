package imat.viewcontroller;

import imat.viewcontroller.ContentViewController;
import imat.viewcontroller.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ProductCategory;

public class RootViewController extends ViewController {


    @FXML private BorderPane borderPane;
    ContentViewController content;

    @Override
    public void initialize() {
        // TODO: Perform initialization!
    }

    public void setContent(ContentViewController c){
        this.content=c;
        ProductCategory pcs [] = ProductCategory.values();
        for(ProductCategory ps:pcs){
            categoryButton btn = new categoryButton(ps);
            btn.setOnAction((e) -> productCategorySelected(e));
        }

    }

    private void productCategorySelected(ActionEvent evt){
        //TODO: Choose category and show
    }

}
