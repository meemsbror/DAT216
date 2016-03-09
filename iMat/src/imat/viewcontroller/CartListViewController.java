package imat.viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.*;

import java.util.Map;



public class CartListViewController extends ViewController implements ShoppingCartListener{

    @FXML ListView listView;
    @FXML Text totalPriceText;
    @FXML Text noProductsText;


    ObservableList<Parent> smallProductViews = FXCollections.observableArrayList();
    private Map<Product,Double> cart = RootViewController.getInstance().getCart();



    public void initialize() {
        IMatDataHandler.getInstance().getShoppingCart().addShoppingCartListener(this);
        showCart();
        updatePrice();
    }

    @Override
    protected void viewDidSet(Parent view) {
    }


    public void showCart(){
        if(cart.size()==0){
            noProductsText.setText("Inga varor i varukorgen");
        }
        else{
            noProductsText.setText("");
        }
        smallProductViews.removeAll(smallProductViews);
        for(Product p:cart.keySet()) {
            SmallProductViewController smallProductViewController = SmallProductViewController.load("SmallProductView.fxml");
            smallProductViewController.setItem(new ShoppingItem(p,cart.get(p)), true, false);
            smallProductViews.add(smallProductViewController.getView());
        }
        listView.setItems(smallProductViews);
    }

    @Override
    public void shoppingCartChanged(CartEvent event){
        if(!event.isAddEvent()) {
            showCart();
        }
        updatePrice();

    }

    private void updatePrice(){
        totalPriceText.setText(Double.toString(RootViewController.getInstance().getTotalPrice()) + " kr");
    }
}
