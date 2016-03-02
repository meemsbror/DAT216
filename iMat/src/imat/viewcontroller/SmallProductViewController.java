package imat.viewcontroller;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;;
import javafx.scene.control.Button;

public class SmallProductViewController extends ViewController{
    private int quantity;
    public static final double IMAGE_WIDTH = 100.0;
    public static final double IMAGE_HEIGHT = 80.0;

    @FXML private Label productNameLabel;
    @FXML private Label productPriceLabel;
    @FXML private Label totalPriceLabel;
    @FXML private Button removeProductButton;
    @FXML private TextField quantityTextFeild;
    @FXML private ToggleButton increaseQuantityButton;
    @FXML private ToggleButton decreaseQuantityButton;
    @FXML private ImageView productImageView;
    @FXML private AnchorPane toDetailView;
    @FXML private HBox toDetailView2;

    private Product product;


    @Override
    public void initialize() {

    }

    @Override
    protected void viewDidSet(Parent view) {
        view.setOnMouseClicked(event -> onProductPressed());
    }

    public void incraseQuantity(ActionEvent evt)
    {
        if (evt.getSource().equals("increaseQuantityButton")) {
            if (quantity < 99) {
                quantity++;
            }
            else quantity = 99;
            quantityTextFeild.setText(Integer.toString(quantity));
        }
    }
     public void decreaseQuantity(ActionEvent evt)
     {
         if (evt.getSource().equals("decreaseQuantityButton"))
         {
             if (quantity >0) {
                 quantity--;
             }
             else quantity =0;
             quantityTextFeild.setText(Integer.toString(quantity));
         }
     }

    public void controllQuantity()
    {
        quantity = Integer.parseInt(quantityTextFeild.getText());
        if (quantity>99)
        {
            quantity = 99;
        }
        else if (quantity<0) {
            quantity = 0;
        }
        quantityTextFeild.setText(Integer.toString(quantity));
    }

    public void removeProduct(ActionEvent evt)
    {
        if (evt.getSource().equals("removeProductButton")) {
            int tmp = indexInCart(product);
            IMatDataHandler.getInstance().getShoppingCart().removeItem(tmp);
            CartListViewController cartListViewController = new CartListViewController();
            cartListViewController.showCart();
        }
    }

    public void setItem(ShoppingItem item){

        Product product = item.getProduct();
        this.product = product;

        Image productImage = IMatDataHandler.getInstance().getFXImage(product);
        productImageView.setImage(productImage);
        productNameLabel.setText(product.getName());

        productPriceLabel.setText(String.valueOf(product.getPrice()));
        Double tmp = item.getAmount();
        this.totalPriceLabel.setText(tmp.toString());
    }

    @FXML
    public void onProductPressed(){

        DetailViewController detailViewController = RootViewController.getInstance().getReuseDetailViewController();
        detailViewController.setProduct(this.product);
        detailViewController.setSourceView(RootViewController.getInstance().getReuseCartViewController());
        RootViewController.getInstance().setContent(detailViewController);

    }
}
