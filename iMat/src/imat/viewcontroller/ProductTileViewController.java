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

    public static final double IMAGE_WIDTH = 260.0;
    public static final double IMAGE_HEIGHT = 195.0;

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

        Image productImage = IMatDataHandler.getInstance().getFXImage(product, IMAGE_WIDTH, IMAGE_HEIGHT);
        imageView.setImage(productImage);

        titleLabel.setText(product.getName());

        double price = product.getPrice();
        priceLabel.setText(String.valueOf(price));

    }

    public Product getProduct(){
        return this.product;
    }


    @FXML
    public void onTilePressed() {
        // TODO: Load the product detail view. Might be good to have a singleton RootViewController that you can access when you want to set the content.
        System.out.println("Load product detail view for product " + this.product.getName());
        RootViewController.getInstance().setContent(DetailViewController.load("DetailView.fxml"));
    }

    @FXML
    public void onCartPressed(ActionEvent e) {
        if(e.getSource().equals(addToCartButton)) {
            IMatDataHandler.getInstance().addProduct(this.product);
            //this.addToCartButton.setGraphic(Parent.lookup("kundvagn-ikon-bla"));
        }



    }


    @FXML
    public void onStarPressed(ActionEvent e) {
        if(e.getSource().equals(favoriteButton)){
             IMatDataHandler.getInstance().addFavorite(this.product);
             //this.favoriteButton.setGraphic(Parent.lookup("yellowstar.png"));
        }

    }


}
