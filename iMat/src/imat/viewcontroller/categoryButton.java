package imat.viewcontroller;

import javafx.scene.control.Button;
import se.chalmers.ait.dat215.project.ProductCategory;

public class categoryButton extends Button {

    private final ProductCategory ps;


    public categoryButton(ProductCategory ps){

        this.ps = ps;

        setText(ps.name());


    }

    public ProductCategory getProductCategory(){
        return ps;
    }

}
