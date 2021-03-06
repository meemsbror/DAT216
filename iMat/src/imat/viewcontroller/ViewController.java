package imat.viewcontroller;

import com.sun.istack.internal.NotNull;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import se.chalmers.ait.dat215.project.*;
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
     *     class RootViewController extends ViewController { ... }
     *     RootViewController lvc = RootViewController.load("listView.fxml");
     * </code>
     *
     * @param fxmlPath path of FXML file
     * @param <T> type of the requested view controller
     * @return a view controller with its associated view
     */
    public static <T extends ViewController> T load(String fxmlPath) {

        try {

            // Load the view and view controller
            FXMLLoader loader = new FXMLLoader(ViewController.class.getResource(fxmlPath));
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
            e.printStackTrace();
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
        viewDidSet(view);
    }

    /**
     *
     * Called when the view of this view controller is set. This is a good place setup the view for presentation.
     *
     * @param view the associated view
     */
    protected abstract void viewDidSet(Parent view);

    public static int indexInCart(Product p){

        for(ShoppingItem item: IMatDataHandler.getInstance().getShoppingCart().getItems()){
            if(item.getProduct().equals(p)){
                return IMatDataHandler.getInstance().getShoppingCart().getItems().indexOf(item);
            }

        }
        return -1;
    }

}
