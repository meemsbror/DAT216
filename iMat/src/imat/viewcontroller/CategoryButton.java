package imat.viewcontroller;

import javafx.scene.control.Button;
import se.chalmers.ait.dat215.project.ProductCategory;

public class CategoryButton extends Button {

    private final ProductCategory ps;


    public CategoryButton(ProductCategory ps){

        this.ps = ps;

        setText(ps.name());

    }

    public ProductCategory getProductCategory(){
        return ps;
    }
}
