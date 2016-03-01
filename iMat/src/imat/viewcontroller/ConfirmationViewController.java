package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class ConfirmationViewController extends ContentViewController {

    @FXML AnchorPane showPane;

    @Override
    public void initialize() {

    }

    @Override
    protected void viewDidSet(Parent view) {

    }

    @FXML
    public void confirmPurchaseButtonWasPressed() {
        boolean success = performPurchase();

        if (success) {
            PurchaseDoneViewController pdvc = PurchaseDoneViewController.load("PurchaseDoneView.fxml");
            RootViewController.getInstance().setContent(pdvc);
        }
    }

    private boolean performPurchase() {
        // TODO: Implement!
        return true;
    }

    public void showCart(){
        CartListViewController cartListViewController = new CartListViewController().load("CartListView.fxml");
        cartListViewController.showCart();
        showPane.getChildren().add(cartListViewController.getView());
    }

}
