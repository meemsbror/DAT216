package imat.viewcontroller;


import imat.formatting.PriceFormatter;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 * Created by Kotex on 02/03/2016.
 */
public class SmallHistoryProductViewController extends ViewController {

    @FXML Label nameLabel;
    @FXML Label priceLabel;
    @FXML Label totalLabel;
    @FXML Label amountLabel;
    @FXML ImageView imageView;
    @FXML Button addToCart;


    private ShoppingItem item;
    private Product product;
    private double quantity;
    @Override
    protected void viewDidSet(Parent view) {

    }

    @Override
    public void initialize() {

    }

    public void setItem(ShoppingItem item) {
        this.item = item;

        product = this.item.getProduct();
        quantity = this.item.getAmount();

        Image productImage = IMatDataHandler.getInstance().getFXImage(product);
        imageView.setImage(productImage);
        nameLabel.setText(product.getName());
        priceLabel.setText(String.valueOf(PriceFormatter.getFormattedPrice(product)));
        amountLabel.setText(Double.toString(item.getAmount()));
        totalLabel.setText(Double.toString(item.getTotal()));
    }

    public void addToCart()
    {
        IMatDataHandler.getInstance().getShoppingCart().addProduct(product,quantity);
    }
}