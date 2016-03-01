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
        ConfirmationViewController cvc = new ConfirmationViewController().load("ConfirmationView.fxml");
        // TODO: Give the ConfirmationViewController the information that should be confirmed!
        RootViewController.getInstance().setContent(cvc);
        cvc.showCart();
    }

    public void showCart(){
        System.out.println("Bajs");
        CartListViewController cartListViewController = new CartListViewController().load("CartListView.fxml");
        cartListViewController.showCart();
        cartPane.getChildren().add(cartListViewController.getView());

    }
}
