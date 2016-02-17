package imat.viewcontroller;

import com.sun.istack.internal.NotNull;
import imat.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public abstract class ViewController {

    /**
     *
     * The view associated with this view controller.
     *
     */
    @NotNull
    private Parent view;

    /**
     *
     * Returns the view handled by this view controller
     *
     * @return the managed view
     */
    public Parent getView() {
        return view;
    }

    /**
     *
     * Will be called by JavaFX when this view controller is loaded.
     * The view field is <i>not</i> available at this point in time.
     *
     */
    public abstract void initialize();

    /**
     *
     * Load a view controller with its associated view from an FXML file.
     *
     * Example:
     * <code>
     *     class ListViewController extends ViewController { ... }
     *     ListViewController lvc = ListViewController.load("listView.fxml");
     * </code>
     *
     * @param fxmlPath path of FXML file
     * @param <T> type of the requested view controller
     * @return a view controller with its associated view
     */
    public static <T extends ViewController> T load(String fxmlPath) {

        try {

            // Load the view and view controller
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
            Parent view = loader.load();
            T viewController = loader.<T>getController();

            // Assert the view has an associated controller
            if (viewController == null) {
                throw new IllegalStateException("View has no associated controller.");
            }

            // Set the connected view of the view controller.
            viewController.setView(view);

            return viewController;

        } catch (Exception e) {
            // Replace shitty message
            String message = e.getMessage().equals("Location is not set.") ? "No file at specified path" : e.getMessage();
            System.err.println("Could not load XFML view using path: \"" + fxmlPath + "\". Reason: " + message);
            return null;
        }
    }

    /**
     *
     * Set the view for this ViewController. The supplied view <b>must</b> be connected to this view controller!
     *
     * <i>NOTE:</i> This should only ever be called by the static load() method of ViewController!
     *
     * @param view supplied view
     */
    protected void setView(Parent view) {
        this.view = view;
    }

}
