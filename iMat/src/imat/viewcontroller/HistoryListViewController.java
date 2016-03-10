package imat.viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;


public class HistoryListViewController extends ViewController{
    @FXML
    ListView listView;


    ObservableList<Parent> smallHistoryProductViews = FXCollections.observableArrayList();

    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {
    }



    public void shoppingCartChanged(CartEvent event){

    }


    public void showOrders(Order order){

        smallHistoryProductViews.removeAll(smallHistoryProductViews);
        for(ShoppingItem shopItem: order.getItems()) {

            SmallProductViewController smallHistoryProductViewController = SmallProductViewController.load("SmallProductView.fxml");
            smallHistoryProductViewController.setItem(shopItem, false, true);
            smallHistoryProductViews.add(smallHistoryProductViewController.getView());

        }
        listView.setItems(smallHistoryProductViews);
    }
}
