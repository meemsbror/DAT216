package imat.viewcontroller;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class CartViewController extends ContentViewController{

    @FXML private AnchorPane putCart;

    @FXML private Button toCheckOutButton;
    private Product productInCart;

    @Override
    public void initialize() {

    }

    @Override
    protected void viewDidSet(Parent view) {

    }

    public void setCart(List<ShoppingItem> cart){
       //Don't know if this is needed

    }

    public void showCart(){
        CartListViewController cartListViewController = CartListViewController.load("CartListView.fxml");
        CartViewController cartViewController;
        putCart.getChildren().add(cartListViewController.getView());

    }

    public void toCheckout(ActionEvent evt){
        if(evt.getSource().equals(toCheckOutButton)){
            CheckOutViewController checkOutViewController = CheckOutViewController.load("CheckOutView.fxml");
            RootViewController.getInstance().setContent(checkOutViewController);
        }
    }

}
