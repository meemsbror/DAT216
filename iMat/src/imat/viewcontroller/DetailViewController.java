package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;


public class DetailViewController extends ContentViewController {

    @FXML private ImageView productImage;
    @FXML private Text productName;
    @FXML private Text productPrice;



    @Override
    public void initialize() {

    }

    @Override
    protected void viewDidSet(Parent view) {

    }




    public void setTitle(Product product){
        productName.setText(ProductTileViewController.getProduct().getName());
    }

    public void setPrice(Product product){
        double price = product.getPrice();
        productPrice.setText(String.valueOf(price));
    }

}
