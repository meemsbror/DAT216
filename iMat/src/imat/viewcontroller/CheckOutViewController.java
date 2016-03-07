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
        showCustomer();
    }

    @Override
    protected void viewDidSet(Parent view) {

    }

    public void showCustomer(){
        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        address.setText(customer.getAddress());
        zipCode.setText(customer.getPostCode());
        city.setText(customer.getPostAddress());
    }

    @FXML
    public void nextButtonWasPressed() {
        ConfirmationViewController cvc = ConfirmationViewController.load("ConfirmationView.fxml");
        cvc.setConfirmation(firstName.getText(),
                lastName.getText(),
                address.getText(),
                zipCode.getText(),
                city.getText(),
                cardNumber.getText(),
                cvcCode.getText());
        if(saveChangesBox.isSelected()){
            setCustomer();
        }
        RootViewController.getInstance().setContent(cvc);
        //cvc.showCart();
    }

    private void setCustomer(){
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setAddress(address.getText());
        customer.setPostCode(zipCode.getText());
        customer.setPostAddress(city.getText());

        System.out.println(IMatDataHandler.getInstance().isCustomerComplete());
    }
    public void showCart(){
        CartListViewController cartListViewController = CartListViewController.load("CartListView.fxml");
        cartListViewController.showCart();
        cartPane.getChildren().add(cartListViewController.getView());
    }
}
