package imat.viewcontroller;

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
