package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;

import java.util.ArrayList;

/**
 * Created by Kotex on 02/03/2016.
 */
public class HistoryPageViewController extends ContentViewController {
    @FXML AnchorPane firstOrderList;
    @FXML AnchorPane secondOrderList;
    @FXML AnchorPane thirdOrderList;

    @FXML Button addFirstList;
    @FXML Button addSecondList;
    @FXML Button addThirdList;
    @FXML Button nextButton;
    @FXML Button backButton;

    @FXML TitledPane first;
    @FXML TitledPane second;
    @FXML TitledPane third;



    private static ArrayList<Order> orders = (ArrayList<Order>) IMatDataHandler.getInstance().getOrders();
    private static int amountOrder = orders.size();
    private static int indexOrder = 0;
    private static int pageNumber = 0;

    @Override
    protected void viewDidSet(Parent view) {

    }

    @Override
    public void initialize() {

    }
    public void addProduct(AnchorPane p)
    {
        HistoryListViewController historyListViewController = new HistoryListViewController().load("HistoryListView.fxml");
        historyListViewController.showCart(orders.get(indexOrder));
        p.getChildren().add(historyListViewController.getView());
        indexOrder ++;
    }

    public void showHistoryList()
    {
        if (pageNumber == 0)
        {
            backButton.setDisable(true);
        }
        addProduct(firstOrderList);
        if (checkNextOrder())
        {
            addProduct(secondOrderList);
            if (checkNextOrder())
            {
                addProduct(thirdOrderList);
                if (checkNextOrder() == false)
                {
                    this.nextButton.setDisable(true);
                }
            }
        }
    }
    public boolean checkNextOrder()
    {
        return ((amountOrder-1)>indexOrder);
    }

}
