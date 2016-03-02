package imat.viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import se.chalmers.ait.dat215.project.*;
import java.util.Map;



public class CartListViewController extends ViewController implements ShoppingCartListener{

    @FXML ListView listView;
    ObservableList<Parent> smallProductViews = FXCollections.observableArrayList();
    private Map<Product,Double> cart = RootViewController.getInstance().getCart();



    public void initialize() {
        IMatDataHandler.getInstance().getShoppingCart().addShoppingCartListener(this);
        showCart();
    }

    @Override
    protected void viewDidSet(Parent view) {
    }


    public void showCart(){
        smallProductViews.removeAll(smallProductViews);

        for(Product p:cart.keySet()) {
            SmallProductViewController smallProductViewController = SmallProductViewController.load("SmallProductView.fxml");
            smallProductViewController.setItem(new ShoppingItem(p,cart.get(p)));
            smallProductViews.add(smallProductViewController.getView());
        }
        listView.setItems(smallProductViews);
    }

    @Override
    public void shoppingCartChanged(CartEvent event){
        if(!event.isAddEvent()){
            showCart();
        }
    }
}
