package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import javafx.scene.text.Text;

public class ConfirmationViewController extends ContentViewController {

    @FXML AnchorPane showPane;

    private Customer customer = IMatDataHandler.getInstance().getCustomer();

    @FXML Text firstNameText;
    @FXML Text lastNameText;
    @FXML Text addressText;
    @FXML Text zipCodeText;
    @FXML Text cityText;
    @FXML Text cardNumberText;
    @FXML Text cvcCodeText;

    @Override
    public void initialize() {
    }

    public void setConfirmation(String firstName,
                                String lastName,
                                String address,
                                String zipCode,
                                String city,
                                String cardNumber,
                                String cvcNumber){


        firstNameText.setText(firstName);
        lastNameText.setText(lastName);
        addressText.setText(address);
        zipCodeText.setText(zipCode);
        cityText.setText(city);
        cardNumberText.setText(cardNumber);
        cvcCodeText.setText(cvcNumber);

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
        CartListViewController cartListViewController = CartListViewController.load("CartListView.fxml");
        cartListViewController.showCart();
        showPane.getChildren().add(cartListViewController.getView());
    }
}
