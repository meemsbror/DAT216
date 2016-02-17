package imat.viewcontroller;

import javafx.scene.control.Button;
import se.chalmers.ait.dat215.project.ProductCategory;

/**
 * Created by frej on 2/17/16.
 */
public class categoryButton extends Button {

    private final ProductCategory ps;


    public categoryButton(ProductCategory ps){

        this.ps = ps;

    }

    public ProductCategory getProductCategory(){
        return ps;
    }

}
