package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.util.List;

/**
 * Created by rebeccafinne on 16-02-28.
 */
public class CartViewController extends ContentViewController {

    @FXML private Label productName;
    @FXML private ImageView smallImage;
    @FXML private Label price;
    @FXML private Label sum;
    @FXML private ScrollPane cartScroll;
    @FXML private ListView cartList;

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

    public void showCart(List<Product> shoppingCart){

        for(int i = 0; i < shoppingCart.size(); i++){
            SmallProductViewController smallProductViewController = SmallProductViewController.load("SmallProductView.fxml");
            smallProductViewController.setProduct(productInCart);

            cartList.add(shoppingCart.get(i));

        }
    }


}
