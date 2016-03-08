package imat.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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


    //TODO: fill in better error descriptions
    private boolean correctInput(){
        boolean allGood = true;
        if(firstName.getText().equals("")){
            firstName.setPromptText("Wrong");
            firstName.setStyle("-fx-text-box-border: red;");
            allGood=false;
        }
        if(lastName.getText().equals("")){
            lastName.setPromptText("Wrong");
            lastName.setStyle("-fx-text-box-border: red;");
            allGood=false;
        }
        if(address.getText().equals("")){
            address.setPromptText("Wrong");
            address.setStyle("-fx-text-box-border: red;");
            allGood=false;
        }
        if(zipCode.getText().equals("")){
            zipCode.setPromptText("Wrong");
            zipCode.setStyle("-fx-text-box-border: red;");
            allGood=false;
        }
        if(city.getText().equals("")){
            city.setPromptText("Wrong");
            city.setStyle("-fx-text-box-border: red;");
            allGood=false;
        }
        if(cvcCode.getText().equals("")){
            cvcCode.setPromptText("Wrong");
            cvcCode.setStyle("-fx-text-box-border: red;");
            allGood=false;
        }
        if(cardNumber.getText().equals("")){
            cardNumber.setPromptText("Wrong");
            cardNumber.setStyle("-fx-text-box-border: red;");
            allGood=false;
        }
        return allGood;
    }

    @FXML
    public void nextButtonWasPressed() {
        if(correctInput()) {


            ConfirmationViewController cvc = ConfirmationViewController.load("ConfirmationView.fxml");
            cvc.setConfirmation(firstName.getText(),
                    lastName.getText(),
                    address.getText(),
                    zipCode.getText(),
                    city.getText(),
                    cardNumber.getText(),
                    cvcCode.getText());
            if (saveChangesBox.isSelected()) {
                setCustomer();
            }
            RootViewController.getInstance().setContent(cvc);
            //cvc.showCart();
        }
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

    private boolean isNumber(KeyEvent event){
        return event.getCharacter().matches("[0-9]");
    }

    public void onKeyTyped(KeyEvent event){

        if(event.getSource()==zipCode){

            if(zipCode.getText().replaceAll("\\s+","").length()==5 || zipCode.getText().length()==6){
                event.consume();
            }
            else if(!isNumber(event) && !event.getCharacter().matches("\\s+")){
                event.consume();
            }
        }
        else if(event.getSource()==cvcCode){
            if(cvcCode.getText().replaceAll("\\s+","").length()==3|| !isNumber(event)){
                event.consume();
            }
        }
        else if((event.getSource()==cardNumber && !isNumber(event)) || cardNumber.getText().length()==16){
            event.consume();
        }
    }
}
