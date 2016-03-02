package imat;

import imat.viewcontroller.RootViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.ait.dat215.project.IMatDataHandler;

import java.io.File;

public class Main extends Application {

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("iMat");

        // Set min size on the stage, since it doesn't seem to listen to any minimum constraints on the scene.
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(665);

        // Create scene
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //double width = screenSize.getWidth();
        //double height = screenSize.getHeight();
        RootViewController rootViewController = RootViewController.getInstance();
        Scene scene = new Scene(rootViewController.getView(), 1150, 745);
        primaryStage.setScene(scene);

        // Add css styling to scene (scene won't change through the runtime)
        File cssFile = new File("src/imat/viewcontroller/style.css");
        String cleanedUpPath = "file://" + cssFile.getAbsolutePath().replace('\\', '/');
        scene.getStylesheets().add(cleanedUpPath);

        // Save state when closing down
        primaryStage.setOnCloseRequest(event -> IMatDataHandler.getInstance().shutDown());

        primaryStage.show();
    }
}