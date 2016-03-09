package imat.viewcontroller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotex on 02/03/2016.
 */
public class HistoryPageViewController extends ContentViewController {
    @FXML Accordion main;

    private static List<Order> orders;
    private int amountOrder;
    private int indexOrder=0;
    @Override
    protected void viewDidSet(Parent view) {

    }

    @Override
    public void initialize() {

    }

    public void showHistoryList(List<Order> orders)
    {
        this.orders = orders;
        amountOrder=orders.size();
        indexOrder=0;
        TitledPane[] tps = new TitledPane[amountOrder];
        while (indexOrder<amountOrder)
        {
            HistoryListViewController historyListViewController = HistoryListViewController.load("HistoryListView.fxml");
            historyListViewController.showOrders(orders.get(indexOrder));
            tps[indexOrder] = new TitledPane(orders.get(indexOrder).getDate().toString(), historyListViewController.getView());
            indexOrder++;
        }
        main.getPanes().addAll(tps);
    }
    public boolean checkNextOrder()
    {
        return ((amountOrder-1)>indexOrder);
    }
}
