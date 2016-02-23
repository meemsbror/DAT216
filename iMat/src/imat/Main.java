package imat;

import imat.viewcontroller.RootViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class Main extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("iMat");

        RootViewController rootViewController = RootViewController.load("RootView.fxml");

        // Create scene
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Scene scene = new Scene(rootViewController.getView(), width, height);
        primaryStage.setScene(scene);

        // Add css styling to scene (scene won't change through the runtime)
        File cssFile = new File("src/imat/viewcontroller/style.css");
        String cleanedUpPath = "file://" + cssFile.getAbsolutePath().replace('\\', '/');
        scene.getStylesheets().add(cleanedUpPath);

        primaryStage.show();
    }
}