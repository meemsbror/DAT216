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

        String formattedPrice = PriceFormatter.getFormattedPrice(product);
        priceLabel.setText(formattedPrice);

    }

    @FXML
    public void onMouseEnter() {
        getView().getStyleClass().add("product-tile-hover");
    }

    @FXML
    public void onMouseExit() {
        getView().getStyleClass().remove("product-tile-hover");
    }





    @FXML
    public void onTilePressed() {
        DetailViewController detailViewController = RootViewController.getInstance().getReuseDetailViewController();
        detailViewController.setProduct(this.product);
        detailViewController.setSourceView(RootViewController.getInstance().getReuseListView());
        RootViewController.getInstance().setContent(detailViewController);
    }

    @FXML
    public void onCartPressed(ActionEvent e) {
        if(e.getSource().equals(addToCartButton)) {
            ShoppingItem item = new ShoppingItem(product, 1.0);
            IMatDataHandler.getInstance().getShoppingCart().addItem(item);
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
