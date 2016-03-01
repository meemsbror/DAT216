package imat.viewcontroller;

import imat.filter.AllProductsProductFilter;
import javafx.fxml.FXML;
import javafx.scene.Parent;

public class PurchaseDoneViewController extends ContentViewController {

    @Override
    public void initialize() {

    }

    @Override
    protected void viewDidSet(Parent view) {

    }

    @FXML
    public void backToStoreButtonWasPressed() {
        ListViewController lvc = RootViewController.getInstance().getReuseListView();
        lvc.setProductFilter(new AllProductsProductFilter());
        RootViewController.getInstance().setContent(lvc);
    }

}
