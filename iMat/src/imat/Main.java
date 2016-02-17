package imat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import java.util.List;

public class Main extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Should print some list of products if everything is set up correctly!
        List<Product> productList = IMatDataHandler.getInstance().getProducts();
        System.out.println(productList);

        primaryStage.setTitle("iMat");

        ListViewController lvc = ListViewController.load("ListView.fxml");

        Scene scene = new Scene(lvc.getView(), 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
