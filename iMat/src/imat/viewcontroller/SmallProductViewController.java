package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;


/**
 * Created by rebeccafinne on 16-02-29.
 */
public class SmallProductViewController extends ContentViewController {

    @FXML private Label productName;
    @FXML private ImageView smallImage;
    @FXML private Label price;
    @FXML private Label sum;

    @Override
    public void initialize() {

    }

    @Override
    protected void viewDidSet(Parent view) {

    }

    public void setItem(ShoppingItem item){

        Product product = item.getProduct();
        Image productImage = IMatDataHandler.getInstance().getFXImage(product);
        smallImage.setImage(productImage);
        productName.setText(product.getName());

        price.setText(String.valueOf(product.getPrice()));
        Double tmp = item.getAmount();
        this.sum.setText(tmp.toString());
    }
}
