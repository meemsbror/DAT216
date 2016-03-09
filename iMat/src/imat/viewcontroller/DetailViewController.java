package imat.viewcontroller;

import imat.filter.FavouritesProductFilter;
import imat.formatting.PriceFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import java.util.Map;


public class DetailViewController extends ContentViewController {

    @FXML private ImageView productImage;
    @FXML private Text productName;
    @FXML private Text productPrice;
    @FXML private Button addToFavoriteButton;
    @FXML private Button removeFavoriteButton;
    @FXML private Button backButton;
    @FXML private Button addToCartButton;
    @FXML private TextField amountCalculator;
    @FXML private Button amountDown;
    @FXML private Button amountUp;
    @FXML private Label unitLabel;
    @FXML private Text feedBackText;

    private Product activeProduct;
    private ContentViewController sourceViewController;
    private double quantity = 1.0;
    private boolean inFavorites;


    @Override
    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {
    }

    public void setProduct(Product p) {

        this.activeProduct = p;

        this.quantity = 1.0;
        updateLabels();

        setTitle();
        setPrice();
        setProductImage();
        unitLabel.setText(activeProduct.getUnitSuffix());

        inFavorites = IMatDataHandler.getInstance().isFavorite(activeProduct);
        changeFavoriteButton();
    }

    private void updateLabels() {
        updateCalculatorValue();
        setFeedBackText();
    }

    private void updateCalculatorValue() {
        String calculatorTextValue;
        if (productAllowsFractionalUnits(activeProduct)) {
            calculatorTextValue = String.valueOf(this.quantity);
        } else {
            long integerQuantity = Math.round(this.quantity);
            calculatorTextValue = String.valueOf(integerQuantity);
        }
        this.amountCalculator.setText(calculatorTextValue);
    }

    public void setTitle() {
        productName.setText(activeProduct.getName());
    }

    public void setPrice(){
        productPrice.setText(String.valueOf(PriceFormatter.getFormattedPrice(activeProduct)));
    }

    public void setProductImage(){
        Image detailImage = IMatDataHandler.getInstance().getFXImage(activeProduct, 247, 214);
        productImage.setImage(detailImage);

    }

    public void setFeedBackText(){

        Map<Product, Double> cart = RootViewController.getInstance().getCart();
        double inCartQuantity = 0.0;
        if (cart.containsKey(activeProduct)) {
            inCartQuantity = cart.get(activeProduct);
        }

        if(inCartQuantity > 0){
            String totalPriceFormatted = PriceFormatter.getFormattedPriceWithoutUnit(activeProduct, inCartQuantity);
            String feedbackText = "Produkten finns i kundvagnen (" + inCartQuantity + " " + activeProduct.getUnitSuffix() +
                    " för " + totalPriceFormatted + "kr)";

            feedBackText.setText(feedbackText);
        }else{
            feedBackText.setText("Produkten finns ej i kundvagnen");
        }
    }

    @FXML
    public void upButtonWasPressed() {
        quantity += 1.0;
        updateLabels();
    }

    @FXML
    public void downButtonWasPressed() {
        quantity = Math.max(0.0, quantity - 1.0);
        updateLabels();
    }

    @FXML
    public void onKeyTypedInCalculator(KeyEvent event) {
        consumeIllegalCharacters(event);
        String newText = amountCalculator.getText() + event.getCharacter();
        try {
            if (productAllowsFractionalUnits(activeProduct)) {
                this.quantity = Double.parseDouble(newText);
            } else {
                this.quantity = (double) Integer.parseInt(newText);
            }
        } catch (NumberFormatException e) {
            //System.err.println("Couldn't parse quantity text! This should be taken care of by deleting illegal characters, " +
            //        "however it seems like it didn't...");
        }
    }

    private void consumeIllegalCharacters(KeyEvent event) {
        String typedCharacter = event.getCharacter();

        // Consume any leading zeros unless the product allows fractional units
        if (amountCalculator.getText().isEmpty() && typedCharacter.equals("0") && !productAllowsFractionalUnits(activeProduct)) {
            event.consume();
        }

        // Consume non-numeric characters ...
        if (!typedCharacter.matches("[0-9]")) {

            // If it's "." or "," ...
            if (typedCharacter.matches("\\.|,")) {

                if (productAllowsFractionalUnits(activeProduct)) {

                    // Don't allow a comma or dot at the beginning of the string
                    if (amountCalculator.getText().isEmpty()) {
                        event.consume();
                    }

                    // Only allow a maximum of one decimal point
                    if (amountCalculator.getText().contains(".") || amountCalculator.getText().contains(",")) {
                        event.consume();
                    }

                } else {

                    // If this product only is sold in whole quantities, consume commas and dots.
                    event.consume();
                }

            } else {
                // Consume all other non-numerical characters
                event.consume();
            }
        }
    }


    private boolean productAllowsFractionalUnits(Product product) {
        return !(product.getUnitSuffix().equals("förp") || product.getUnitSuffix().equals("st"));
    }

    public void addAndRemoveToFavorites(ActionEvent evt){
        if(evt.getSource().equals(addToFavoriteButton)) {
            changeFavoriteButton();
        }

        if (sourceViewController instanceof ListViewController) {
            ListViewController lvc = (ListViewController) sourceViewController;
            if (lvc.getProductFilter() instanceof FavouritesProductFilter) {
                // If the source is a ListView with a FavouritesProductFilter, make sure it updates its list
                // since the addAndRemoveToFavourites method is bound to change its content.
                lvc.refetchProducts();
            }
        }

    }

    private void changeFavoriteButton(){
        if(inFavorites) {
            IMatDataHandler.getInstance().addFavorite(activeProduct);
            addToFavoriteButton.setText("Ta bort från favoriter");
        }
        else {
            IMatDataHandler.getInstance().removeFavorite(activeProduct);
            addToFavoriteButton.setText("Lägg till i favoriter");
        }
        inFavorites= !inFavorites;

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
        if(evt.getSource().equals(addToCartButton)) {
            System.out.println(activeProduct.getName() + "Tillagd i kundvagn");
            IMatDataHandler.getInstance().getShoppingCart().addProduct(activeProduct, quantity);
            System.out.println(IMatDataHandler.getInstance().getShoppingCart().getItems().get(0).getProduct().getName());

            updateLabels();
        }
    }
}
