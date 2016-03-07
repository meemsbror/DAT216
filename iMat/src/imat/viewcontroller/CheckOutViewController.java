package imat.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;

public class CheckOutViewController extends ContentViewController {

    Customer customer = IMatDataHandler.getInstance().getCustomer();

    @FXML AnchorPane cartPane;
    @FXML TextField firstName;
    @FXML TextField lastName;
    @FXML TextField address;
    @FXML TextField zipCode;
    @FXML TextField city;
    @FXML TextField cardNumber;
    @FXML TextField cvcCode;
    @FXML CheckBox saveChangesBox;

    @Override
    public void initialize() {
        showCart();
    }

    @Override
    protected void viewDidSet(Parent view) {

    }

    @FXML
    public void nextButtonWasPressed() {
        ConfirmationViewController cvc = ConfirmationViewController.load("ConfirmationView.fxml");
        // TODO: Give the ConfirmationViewController the information that should be confirmed!
        cvc.setCardNumber(cardNumber.getText(),cvcCode.getText());
        setCustomer();
        RootViewController.getInstance().setContent(cvc);
        //cvc.showCart();
    }

    private void setCustomer(){
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setAddress(address.getText());
        customer.setPostCode(zipCode.getText());
        customer.setPostAddress(city.getText());

    }
    public void showCart(){
        CartListViewController cartListViewController = CartListViewController.load("CartListView.fxml");
        cartListViewController.showCart();
        cartPane.getChildren().add(cartListViewController.getView());
    }

    public void saveData(ActionEvent evt){
        if(evt.getSource().equals(saveChangesBox)){
            
        }
    }
}
