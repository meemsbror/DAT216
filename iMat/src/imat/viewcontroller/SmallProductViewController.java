package imat.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;;

/**
 * Created by Long on 26/02/2016.
 */
public class SmallProductViewController extends ViewController{
    private int quantity;
    public static final double IMAGE_WIDTH = 100.0;
    public static final double IMAGE_HEIGHT = 80.0;

    @FXML private Label productNameLabel;
    @FXML private Label productPriceLabel;
    @FXML private Label totalPriceLabel;
    @FXML private ToggleButton removeProductButton;
    @FXML private TextField quantityTextFeild;
    @FXML private ToggleButton increaseQuantityButton;
    @FXML private ToggleButton decreaseQuantityButton;
    @FXML private ImageView productImage;

    @Override
    public void initialize() {

    }

    @Override
    protected void viewDidSet(Parent view) {

    }

    public void incraseQuantity(ActionEvent evt)
    {
        if (evt.getSource().equals("increaseQuantityButton")) {
            if (quantity < 99) {
                quantity++;
            }
            else quantity = 99;
            quantityTextFeild.setText(Integer.toString(quantity));
        }
    }
     public void decreaseQuantity(ActionEvent evt)
     {
         if (evt.getSource().equals("decreaseQuantityButton"))
         {
             if (quantity >0) {
                 quantity--;
             }
             else quantity =0;
             quantityTextFeild.setText(Integer.toString(quantity));
         }
     }

    public void controllQuantity()
    {
        quantity = Integer.parseInt(quantityTextFeild.getText());
        if (quantity>99)
        {
            quantity = 99;
        }
        else if (quantity<0) {
            quantity = 0;
        }
        quantityTextFeild.setText(Integer.toString(quantity));
    }

    public void removeProduct(ActionEvent evt)
    {
        if (evt.getSource().equals("removeProductButton"))
        {
            
        }
    }




}
