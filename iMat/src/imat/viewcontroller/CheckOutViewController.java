package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * Created by rebeccafinne on 16-02-29.
 */
public class CheckOutViewController extends ContentViewController {

    @FXML AnchorPane cartPane;

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
        cvc.showCart();
    }

    public void showCart(){
        CartListViewController cartList = CartListViewController.load("CartListView.fxml");
        cartPane.getChildren().add(cartList.getView());
    }
}
