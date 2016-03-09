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

import java.util.Iterator;
import java.util.List;

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
            Iterator<ShoppingItem> cartIterator = IMatDataHandler.getInstance().getShoppingCart().getItems().iterator();

            while(cartIterator.hasNext()){
                ShoppingItem item = cartIterator.next();
                if(item.getProduct().equals(this.item.getProduct())){
                    cartIterator.remove();
                    IMatDataHandler.getInstance().getShoppingCart().fireShoppingCartChanged(item,false);
                }
            }
            System.out.println(IMatDataHandler.getInstance().getShoppingCart().getItems().size());
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
