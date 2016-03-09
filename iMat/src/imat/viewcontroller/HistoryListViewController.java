package imat.viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import se.chalmers.ait.dat215.project.CartEvent;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

import javax.swing.text.View;

/**
 * Created by Kotex on 01/03/2016.
 */
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

            SmallHistoryProductViewController smallHistoryProductViewController = new SmallHistoryProductViewController().load("SmallHistoryProductView.fxml");
            smallHistoryProductViewController.setItem(shopItem);
            smallHistoryProductViews.add(smallHistoryProductViewController.getView());
        }
        listView.setItems(smallHistoryProductViews);
    }
}
