package imat.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;


public class DetailViewController extends ContentViewController {

    @FXML private ImageView productImage;
    @FXML private Text productName;
    @FXML private Text productPrice;
    @FXML private Button addToFavoriteButton;

    @Override
    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {
    }

    public void setTitle(Product product) {
        productName.setText(product.getName());
    }

    public void setPrice(Product product){
        productPrice.setText(String.valueOf(product.getPrice()));

    }

    public void setProductImage(Product product){
        Image detailImage = IMatDataHandler.getInstance().getFXImage(product, 247, 214);
        productImage.setImage(detailImage);

    }

   /* public void addToFavorites(ActionEvent evt){
        evt.getSource().equals(addToFavoriteButton);
        IMatDataHandler.getInstance().addFavorite(getProductId());
    }*/

}
