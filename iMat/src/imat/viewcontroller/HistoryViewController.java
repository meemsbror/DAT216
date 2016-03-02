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

/**
 * Created by Long on 26/02/2016.
 */
public class HistoryViewController  extends ContentViewController {

    private HistoryPageViewController reusePageViewController = HistoryPageViewController.load("HistoryPageView.fxml");

    @Override
    public void initialize() {

    }

    @Override
    protected void viewDidSet(Parent view) {

    }
}
