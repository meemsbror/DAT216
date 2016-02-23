package imat.viewcontroller;

import imat.model.ProductFilter;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;

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

    @Override
    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {

    }

    public void setProductFilter(ProductFilter productFilter) {
        this.productFilter = productFilter;
        productFilterNameLabel.setText(productFilter.getName());
    }

    

}
