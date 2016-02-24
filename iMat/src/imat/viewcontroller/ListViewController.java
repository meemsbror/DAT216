package imat.viewcontroller;

import imat.model.ProductFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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

    private List<Product> products = new ArrayList<>();

    private ProductFilter productFilter;
    private SortDescriptor sortDescription;

    @FXML private Label productFilterNameLabel;

    @FXML private RadioButton aToOsortRadioButton;
    @FXML private RadioButton oToAsortRadioButton;
    @FXML private RadioButton lowToHighSortRadioButton;
    @FXML private RadioButton highToLowSortRadioButton;

    @FXML private ToggleGroup sortDescriptor;

    @FXML private FlowPane flowPane;



    @Override
    public void initialize() {
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
                    if(o1.getPrice()>o2.getPrice())
                        return 1;
                    else if(o1.getPrice()<o2.getPrice())
                        return -1;
                    else return 0;

                case PRICE_DESCENDING:
                    if(o1.getPrice()<o2.getPrice())
                        return 1;
                    else if(o1.getPrice()>o2.getPrice())
                        return -1;
                    else return 0;
                default:
                    return 0;
            }
        });

        displayProducts(this.products);
    }

    private void displayProducts(List<Product> productsToDisplay) {

        // First empty out old products
        flowPane.getChildren().clear();

        for (Product product: productsToDisplay) {

            // TODO: Pool & reuse for performance and load time!
            ProductTileViewController tile = ProductTileViewController.load("ProductTileView.fxml");
            tile.setProduct(product);

            flowPane.getChildren().add(tile.getView());
        }
    }

    private void setToggleGroupRadioButtons(){
        aToOsortRadioButton.setToggleGroup(sortDescriptor);
        oToAsortRadioButton.setToggleGroup(sortDescriptor);
        lowToHighSortRadioButton.setToggleGroup(sortDescriptor);
        highToLowSortRadioButton.setToggleGroup(sortDescriptor);
    }

    @FXML
    public void sortingButtonPressed(ActionEvent event){
        if (event.getSource() instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) event.getSource();
            System.out.println("hej");

            // Set sorting descriptor & sort list
            if (radioButton.equals(aToOsortRadioButton)) setSortDescriptorAndPerformSort(SortDescriptor.ALPHABETICALLY_ASCENDING);
            if (radioButton.equals(oToAsortRadioButton)) setSortDescriptorAndPerformSort(SortDescriptor.ALPHABETICALLY_DESCENDING);
            if (radioButton.equals(lowToHighSortRadioButton)) setSortDescriptorAndPerformSort(SortDescriptor.PRICE_ASCENDING);
            if (radioButton.equals(highToLowSortRadioButton)) setSortDescriptorAndPerformSort(SortDescriptor.PRICE_DESCENDING);
        }
    }


}
