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
import se.chalmers.ait.dat215.project.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class CartViewController extends ContentViewController{

    @FXML private Label productName;
    @FXML private ImageView smallImage;
    @FXML private Label price;
    @FXML private Label sum;
    @FXML private ScrollPane cartScroll;

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

    /*public void showCart(List<Product> shoppingCart){

        for(int i = 0; i < shoppingCart.size(); i++){
            SmallProductViewController smallProductViewController = SmallProductViewController.load("SmallProductView.fxml");
            smallProductViewController.setProduct(productInCart);

            cartList.add(shoppingCart.get(i));

        }
    }
    */
    public void toCheckout(ActionEvent evt){
        if(evt.getSource().equals(toCheckOutButton)){
            CheckOutViewController checkOutViewController = CheckOutViewController.load("CheckOutView.fxml");
            RootViewController.getInstance().setContent(checkOutViewController);
        }
    }

}
