package imat.viewcontroller;

import imat.model.ProductFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListViewController extends ContentViewController {

    private enum SortDescriptor {
        ALPHABETICALLY_ASCENDING,
        ALPHABETICALLY_DESCENDING,
        PRICE_ASCENDING,
        PRICE_DESCENDING
    }

    private final List<ProductTileViewController> reuseTiles = new ArrayList<>();

    private List<Product> products = new ArrayList<>();
    private ProductFilter productFilter;
    private SortDescriptor sortDescription;

    @FXML private Label productFilterNameLabel;

    @FXML private RadioButton aToOsortRadioButton;
    @FXML private RadioButton oToAsortRadioButton;
    @FXML private RadioButton lowToHighSortRadioButton;
    @FXML private RadioButton highToLowSortRadioButton;

    @FXML private FlowPane flowPane;
    @FXML private ScrollPane scrollPane;

    @Override
    public void initialize() {
        scrollPane.viewportBoundsProperty().addListener((observableValue, bounds, newBounds) -> {
            flowPane.setPrefWidth(newBounds.getWidth());
            flowPane.setPrefHeight(newBounds.getHeight());
        });
    }

    @Override
    protected void viewDidSet(Parent view) {

    }

    public void setProductFilter(ProductFilter productFilter) {
        this.productFilter = productFilter;
        productFilterNameLabel.setText(productFilter.getName());

        // Fetch & filter products
        this.products = productFilter.filterProducts(IMatDataHandler.getInstance().getProducts());

        // Reset the sorting radio buttons
        aToOsortRadioButton.setSelected(true);
        setSortDescriptorAndPerformSort(SortDescriptor.ALPHABETICALLY_ASCENDING);
    }

    private void setSortDescriptorAndPerformSort(SortDescriptor sortDescriptor) {
        this.sortDescription = sortDescriptor;
        Collections.sort(this.products, (o1, o2) -> {
            switch (sortDescription) {

                case ALPHABETICALLY_ASCENDING:
                    return o1.getName().compareTo(o2.getName());

                case ALPHABETICALLY_DESCENDING:
                    return o2.getName().compareTo(o1.getName());

                case PRICE_ASCENDING:
                    return Double.compare(o1.getPrice(), o2.getPrice());

                case PRICE_DESCENDING:
                    return Double.compare(o2.getPrice(), o1.getPrice());

                default:
                    return 0;
            }
        });

        displayProducts(this.products);
    }

    private void displayProducts(List<Product> productsToDisplay) {

        // Load tiles from FXML if needed
        int numTilesToCreate = Math.max(productsToDisplay.size() - reuseTiles.size(), 0);
        for (int i = 0; i < numTilesToCreate; i++) {
            ProductTileViewController tile = ProductTileViewController.load("ProductTileView.fxml");
            reuseTiles.add(tile);
        }

        // Empty out old tiles
        flowPane.getChildren().clear();

        // Add new tiles
        for (int productIndex = 0; productIndex < productsToDisplay.size(); productIndex++) {
            ProductTileViewController tile = reuseTiles.get(productIndex);
            tile.setProduct(productsToDisplay.get(productIndex));
            flowPane.getChildren().add(tile.getView());
        }
    }

    @FXML
    public void sortingButtonPressed(ActionEvent event){
        if (event.getSource() instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) event.getSource();

            // Set sorting descriptor & sort list
            if (radioButton.equals(aToOsortRadioButton)) setSortDescriptorAndPerformSort(SortDescriptor.ALPHABETICALLY_ASCENDING);
            if (radioButton.equals(oToAsortRadioButton)) setSortDescriptorAndPerformSort(SortDescriptor.ALPHABETICALLY_DESCENDING);
            if (radioButton.equals(lowToHighSortRadioButton)) setSortDescriptorAndPerformSort(SortDescriptor.PRICE_ASCENDING);
            if (radioButton.equals(highToLowSortRadioButton)) setSortDescriptorAndPerformSort(SortDescriptor.PRICE_DESCENDING);
        }
    }

}
