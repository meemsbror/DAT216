package imat.viewcontroller;

import imat.formatting.PriceFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class SmallProductViewController extends ViewController{

    @FXML private ImageView productImageView;
    @FXML private Label productNameLabel;
    @FXML private Label descriptionLabel;
    @FXML private Button removeProductButton;

    private ShoppingItem item;


    @Override
    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {
        view.setOnMouseClicked(event -> onProductPressed());
    }

    public void removeProduct(ActionEvent evt) {
        if (evt.getSource().equals(removeProductButton)) {
            IMatDataHandler.getInstance().getShoppingCart().removeItem(this.item);
        }
    }

    public void setItem(ShoppingItem item, boolean inHistory){
        this.item = item;

        Product product = this.item.getProduct();
        Image productImage = IMatDataHandler.getInstance().getFXImage(product);
        productImageView.setImage(productImage);
        productNameLabel.setText(product.getName());

        Double productQuantity = 0.0;

        if (RootViewController.getInstance().getCart().containsKey(product)) {
            productQuantity = RootViewController.getInstance().getCart().get(product);

        }

        if (!inHistory) {
            removeProductButton.setVisible(false);
            productQuantity = 0.0; // TODO: fix this!
        }

        String totalPriceFormatted = PriceFormatter.getFormattedPriceWithoutUnit(product, productQuantity);
        String descriptionText = productQuantity + " " + product.getUnitSuffix() +
                " för " + totalPriceFormatted + "kr";
        descriptionLabel.setText(descriptionText);
    }

    @FXML
    public void onProductPressed() {
        DetailViewController detailViewController = RootViewController.getInstance().getReuseDetailViewController();
        detailViewController.setProduct(this.item.getProduct());
        detailViewController.setSourceView(RootViewController.getInstance().getReuseCartViewController());
        RootViewController.getInstance().setContent(detailViewController);
    }
}
