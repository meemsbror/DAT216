package imat.viewcontroller;

import imat.model.ProductFilter;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.awt.event.ActionEvent;

public class ListViewController extends ContentViewController {

    private enum SortingDescriptor {
        ALPHABETICALLY_ASCENDING,
        ALPHABETICALLY_DESCENDING,
        PRICE_ASCENDING,
        PRICE_DESCENDING
    }

    private ProductFilter productFilter;
    private SortingDescriptor sortingDescription;

    @FXML private Label productFilterNameLabel;
    @FXML private RadioButton defaultRadioButton;

    @Override
    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {

    }

    public void setProductFilter(ProductFilter productFilter) {
        this.productFilter = productFilter;
        productFilterNameLabel.setText(productFilter.getName());

        // Reset the sorting radio buttons
        defaultRadioButton.setSelected(true);
    }

    @FXML
    public void sortingRadioButtonWasPressed(ActionEvent event) {
        if (event.getSource() instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) event.getSource();

            String name = radioButton.getText();
            // TODO: Set sorting based on the radio buttons!
        }
    }

    public void performSorting() {
        // TODO: Sort list based on the sortingDescription!
    }

}
