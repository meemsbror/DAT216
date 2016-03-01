package imat.viewcontroller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import se.chalmers.ait.dat215.project.*;

import java.util.List;

public class CartListViewController extends ViewController implements ShoppingCartListener{

    @FXML ListView listView;


    private static ObservableList<Parent> smallProductViews;


    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {
    }



    private CartListViewController(){
    }

    public void shoppingCartChanged(CartEvent event){

    }


    public void showCart(){

        smallProductViews.removeAll(smallProductViews);
        for(ShoppingItem shopItem: IMatDataHandler.getInstance().getShoppingCart().getItems()) {


            SmallProductViewController smallProductViewController = new SmallProductViewController().load("SmallProductView.fxml");
            smallProductViewController.setItem(shopItem);
            smallProductViews.add(smallProductViewController.getView());

        }
        System.out.println("yolo");
        listView.setItems(smallProductViews);
    }
}
