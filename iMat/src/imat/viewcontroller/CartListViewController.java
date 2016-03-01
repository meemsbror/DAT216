package imat.viewcontroller;

import javafx.collections.ObservableList;
import javafx.scene.Parent;
import se.chalmers.ait.dat215.project.*;

import java.util.List;

public class CartListViewController extends ViewController implements ShoppingCartListener{

    private static ObservableList<SmallProductViewController> smallProductViews;


    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {
    }



    private CartListViewController(){
    }

    public void shoppingCartChanged(CartEvent event){

    }


    public void showCart(List<Product> shoppingCart){

        smallProductViews.removeAll(smallProductViews);
        for(ShoppingItem shopItem: IMatDataHandler.getInstance().getShoppingCart().getItems()) {


            SmallProductViewController smallProductViewController = new SmallProductViewController().load("SmallProductView.fxml");
            smallProductViewController.setItem(shopItem);
            smallProductViews.add(smallProductViewController);
        }
    }
}
