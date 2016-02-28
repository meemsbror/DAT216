package imat.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;


public class DetailViewController extends ContentViewController {

    @FXML private ImageView productImage;
    @FXML private Text productName;
    @FXML private Text productPrice;
    @FXML private Button addToFavoriteButton;
    @FXML private Button removeFavoriteButton;
    @FXML private Button backButton;
    @FXML private Button addToCartButton;
    @FXML private TextField amountCalculator;
    @FXML private ToggleButton amountDown;
    @FXML private ToggleButton amountUp;

    private ContentViewController sourceViewController;


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
        if(IMatDataHandler.getInstance().isFavorite(activeProduct)){
            removeFavoriteButton.toFront();
        }else{
            addToFavoriteButton.toFront();
        }
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

    public void addAndRemoveToFavorites(ActionEvent evt){
        if(evt.getSource().equals(addToFavoriteButton)) {
            IMatDataHandler.getInstance().addFavorite(activeProduct);
            this.removeFavoriteButton.toFront();
        }if(evt.getSource().equals(removeFavoriteButton)){
            IMatDataHandler.getInstance().removeFavorite(activeProduct);
            addToFavoriteButton.toFront();
        }
    }

    public void setSourceView(ContentViewController content){
        this.sourceViewController = content;
    }

    public void backToSource(ActionEvent evt){
        if(evt.getSource().equals(backButton)){
            RootViewController.getInstance().setContent(sourceViewController);
        }
    }


    public void addToCart(ActionEvent evt){
        if(evt.getSource().equals(addToCartButton))
            se.chalmers.ait.dat215.project.ShoppingCart.addProduct(activeProduct, Double.parseDouble(amountCalculator.getText()));
    }


}
