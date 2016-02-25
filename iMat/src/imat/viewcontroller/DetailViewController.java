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
    @FXML private Button removeFavoriteButton;

    private Product activeProduct;

    @Override
    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {
    }

    public void setProduct(Product p){
        this.activeProduct=p;
        setTitle();
        setPrice();
        setProductImage();
    }

    public void setTitle() {
        productName.setText(activeProduct.getName());
    }

    public void setPrice(){
        productPrice.setText(String.valueOf(activeProduct.getPrice()));
    }

    public void setProductImage(){
        Image detailImage = IMatDataHandler.getInstance().getFXImage(activeProduct, 247, 214);
        productImage.setImage(detailImage);

    }

    public void addToFavorites(ActionEvent evt){
        if(evt.getSource().equals(addToFavoriteButton)) {
            IMatDataHandler.getInstance().addFavorite(activeProduct);
            this.removeFavoriteButton.toFront();
        }
    }

    public void removeFromFavorite(ActionEvent evt){
        if(evt.getSource().equals(removeFavoriteButton)){
            IMatDataHandler.getInstance().removeFavorite(activeProduct);
            addToFavoriteButton.toFront();
        }
    }


}
