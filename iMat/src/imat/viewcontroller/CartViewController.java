package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import se.chalmers.ait.dat215.project.ShoppingItem;

import java.util.List;

/**
 * Created by rebeccafinne on 16-02-28.
 */
public class CartViewController extends ContentViewController {

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

    public void setCart(List<ShoppingItem> cart){


    }


}
