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
    @FXML TitledPane firstOrderList;
    @FXML TitledPane secondOrderList;
    @FXML TitledPane thirdOrderList;

    @FXML Button addFirstList;
    @FXML Button addSecondList;
    @FXML Button addThirdList;

    @FXML TitledPane first;
    @FXML TitledPane second;
    @FXML TitledPane third;

    private static ArrayList<Order> orders;
    private static int amountOrder;
    private static int indexOrder = 0;
    private static int pageNumber = 0;
    private static boolean nextPageAvailable=false;
    private static boolean previousPageAvailable =false;

    @Override
    protected void viewDidSet(Parent view) {

    }

    @Override
    public void initialize() {

    }
    public void addProduct(TitledPane p)
    {
        HistoryListViewController historyListViewController = new HistoryListViewController().load("HistoryListView.fxml");
        if (orders.size() > 0) {
            historyListViewController.showOrders(orders.get(indexOrder));
            p.setContent(historyListViewController.getView());
            indexOrder++;
        }
    }

    public void reset()
    {
        orders = (ArrayList<Order>) IMatDataHandler.getInstance().getOrders();
        amountOrder =  orders.size();
        indexOrder = 0;
        pageNumber = 0;
        nextPageAvailable = false;
    }

    public void showHistoryList()
    {
        if (pageNumber == 0)
        {
            previousPageAvailable = false;
        }
        else previousPageAvailable= true;

        addProduct(firstOrderList);
        if (checkNextOrder())
        {
            addProduct(secondOrderList);
            if (checkNextOrder())
            {
                addProduct(thirdOrderList);
                if (checkNextOrder() == false)
                {
                   nextPageAvailable = false;
                }
                else nextPageAvailable = true;
            }
        }
    }
    public boolean checkNextOrder()
    {
        return ((amountOrder-1)>indexOrder);
    }
}
