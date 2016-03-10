package imat.viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.*;

import java.util.Map;

public class ConfirmationViewController extends ContentViewController {

    @FXML AnchorPane cartPane;

    private Customer customer = IMatDataHandler.getInstance().getCustomer();

    @FXML private Text firstNameText;
    @FXML private Text lastNameText;
    @FXML private Text addressText;
    @FXML private Text zipCodeText;
    @FXML private Text cityText;
    @FXML private Text cardNumberText;
    @FXML private Text cvcCodeText;
    @FXML private Button backButton;


    @Override
    public void initialize() {
        showCart();
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
        if (performPurchase()) {
            removeCart();
            PurchaseDoneViewController pdvc = PurchaseDoneViewController.load("PurchaseDoneView.fxml");
            RootViewController.getInstance().setContent(pdvc);
        }else{
            //TODO: Explain whats wrong i guess.
        }
    }

    private boolean performPurchase() {
        Order performedOrder = IMatDataHandler.getInstance().placeOrder(false);
        return true;
    }

    private void removeCart(){
        RootViewController.getInstance().removeCart();
    }

    public void showCart(){
        ObservableList<Parent> smallProductViews = FXCollections.observableArrayList();
        Map<Product,Double> cart = RootViewController.getInstance().getCart();

        for(Product p : cart.keySet()) {
            SmallProductViewController smallProductViewController = SmallProductViewController.load("SmallProductView.fxml");
            smallProductViewController.setItem(new ShoppingItem(p, cart.get(p)), false, true);
            smallProductViews.add(smallProductViewController.getView());
        }

        ListView listView = new ListView();
        listView.setPrefWidth(600.0);
        listView.setItems(smallProductViews);
        cartPane.getChildren().add(listView);
    }

    public void backButtonPressed(ActionEvent evt){
        if(evt.getSource().equals(backButton)){
            CheckOutViewController back = CheckOutViewController.load("CheckOutView.fxml");
            RootViewController.getInstance().setContent(back);
        }
    }


}
