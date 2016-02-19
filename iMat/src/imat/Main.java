package imat;

import imat.viewcontroller.RootViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Should print some list of products if everything is set up correctly!
        //List<Product> productList = IMatDataHandler.getInstance().getProducts();
        //System.out.println(productList);

        primaryStage.setTitle("iMat");

        RootViewController rootViewController = RootViewController.load("RootView.fxml");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Scene scene = new Scene(rootViewController.getView(), width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
