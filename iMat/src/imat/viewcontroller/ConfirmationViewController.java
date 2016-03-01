package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;

public class ConfirmationViewController extends ContentViewController {

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

}
