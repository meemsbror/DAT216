package imat.viewcontroller;

import imat.viewcontroller.ContentViewController;
import imat.viewcontroller.ViewController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class RootViewController extends ViewController {


    @FXML private BorderPane borderPane;
    ContentViewController content;

    @Override
    public void initialize() {
        // TODO: Perform initialization!
    }

    public void setContent(ContentViewController c){

        this.content=c;



    }

}
