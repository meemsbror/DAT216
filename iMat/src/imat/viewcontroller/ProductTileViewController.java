package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import java.awt.event.ActionEvent;

public class ProductTileViewController extends ViewController {

    public static final double IMAGE_SIZE = 250.0;

    @FXML private ImageView imageView;
    @FXML private Label titleLabel;
    @FXML private Label priceLabel;
    @FXML private Button favoriteButton;
    @FXML private Button addToCartButton;

    private Product product;

    @Override
    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {
        view.setOnMouseClicked(event -> onTilePressed());
    }

    public void setProduct(Product product) {
        this.product = product;

        // TODO: Make a cropped resolution correct image.
        Image productImage = IMatDataHandler.getInstance().getFXImage(product, IMAGE_SIZE, IMAGE_SIZE);
        imageView.setImage(productImage);

        titleLabel.setText(product.getName());

        double price = product.getPrice();
        priceLabel.setText(String.valueOf(price));

    }

    @FXML
    public void onTilePressed() {
        // TODO: Load the product detail view. Might be good to have a singleton RootViewController that you can access when you want to set the content.
        System.out.println("Load product detail view for product " + this.product.getName());
    }

    @FXML
    public void onCartPressed(ActionEvent e) {
        if(e.getSource().equals(addToCartButton)) {
            IMatDataHandler.addProduct(this.product);
            this.addToCartButton.setGraphic(Parent.lookup("kundvagn-ikon-bla"));
        }



    }

    //Does it say that it's wrong because it's in a static method?
    @FXML
    public void onStarPressed(ActionEvent e) {
        if(e.getSource().equals(favoriteButton)){
             IMatDataHandler.addFavorite(this.product);
             this.favoriteButton.setGraphic(Parent.lookup("yellowstar.png"));
        }





    }

}
