package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;

/**
 * Created by rebeccafinne on 16-02-29.
 */
public class CheckOutViewController extends ContentViewController {

    @Override
    public void initialize() {

    }

    @Override
    protected void viewDidSet(Parent view) {

    }

    @FXML
    public void nextButtonWasPressed() {
        ConfirmationViewController cvc = ConfirmationViewController.load("ConfirmationView.fxml");
        // TODO: Give the ConfirmationViewController the information that should be confirmed!
        RootViewController.getInstance().setContent(cvc);
    }
}
