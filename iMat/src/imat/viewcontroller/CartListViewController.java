package imat.viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ShoppingCartListener;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class CartListViewController extends ViewController implements ShoppingCartListener{

    @FXML ListView listView;

    ObservableList<Parent> smallProductViews = FXCollections.observableArrayList();


    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {
    }



    public void shoppingCartChanged(CartEvent event){

    }


    public void showCart(){
        smallProductViews.removeAll(smallProductViews);
        for(ShoppingItem shopItem: IMatDataHandler.getInstance().getShoppingCart().getItems()) {

            SmallProductViewController smallProductViewController = SmallProductViewController.load("SmallProductView.fxml");
            smallProductViewController.setItem(shopItem);
            smallProductViews.add(smallProductViewController.getView());

        }

        listView.setItems(smallProductViews);
    }
}
