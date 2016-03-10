package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;
import se.chalmers.ait.dat215.project.ShoppingItem;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 26/02/2016.
 */
public class HistoryViewController  extends ContentViewController{

    private HistoryPageViewController reusePageViewController = HistoryPageViewController.load("HistoryPageView.fxml");
    private static HistoryViewController historyViewController = HistoryViewController.load("HistoryView.fxml");

    @FXML AnchorPane p;
    @FXML Button nextButton;
    @FXML Button backButton;


    private static List<Order> allOrders;
    private static List<Order> currentOrders = new ArrayList<>();
    private static int amountOrder;
    private static int indexOrder;
    private static int pageNumber = 1;


    @Override
    public void initialize() {
    }

    @Override
    protected void viewDidSet(Parent view) {
    }

    public static HistoryViewController getInstance()
    {
        return historyViewController;
    }

    private static void getNextCurrentOrders()
    {
        int i = 0;

        currentOrders.clear();
        while (indexOrder >=0&& i<10) {
            currentOrders.add(allOrders.get(indexOrder));
            indexOrder = indexOrder - 1;
            i++;
        }

    }
    private static void getPreviousCurrentOrders()
    {
        int i = 0;
        currentOrders.clear();
        while (i<10)
        {
            indexOrder = indexOrder+10;
            currentOrders.add(allOrders.get(indexOrder));
            indexOrder = indexOrder - 1;
            i++;
        }
    }

    public void showHistory()
    {
        reset();
        reusePageViewController.showHistoryList(currentOrders);
        p.getChildren().add(reusePageViewController.getView());
        setButtonVisibility();
    }

    private static void reset()
    {
        allOrders = IMatDataHandler.getInstance().getOrders();
        currentOrders.clear();
        amountOrder = allOrders.size();
        indexOrder = amountOrder-1;
        pageNumber = 1;
        int i = 0;

        System.out.print(i);
        System.out.println(indexOrder);
        while (indexOrder  >=0 && i<10) {
            currentOrders.add(allOrders.get(indexOrder));
            indexOrder = indexOrder - 1;
            i++;
        }
    }

    public void nextPage()
    {
        getNextCurrentOrders();
        reusePageViewController.showHistoryList(currentOrders);
        p.getChildren().add(reusePageViewController.getView());
        pageNumber++;
        setButtonVisibility();
    }

    public void previousPage()
    {
        getPreviousCurrentOrders();
        reusePageViewController.showHistoryList(currentOrders);
        p.getChildren().add(reusePageViewController.getView());
        pageNumber--;
        setButtonVisibility();
    }

    private void setButtonVisibility()
    {
        if (pageNumber==1)
            backButton.setVisible(false);
        else backButton.setVisible(true);

        if (indexOrder>0)
            nextButton.setVisible(true);
        else backButton.setVisible(false);
    }
}
