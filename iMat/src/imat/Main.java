package imat;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import java.util.List;

public class Main {

    public static void main(String[] args){

        // Should print some list of products if everything is set up correctly!
        List<Product> productList = IMatDataHandler.getInstance().getProducts();
        System.out.println(productList);

    }
}
