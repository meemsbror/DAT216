package imat.filter;

import se.chalmers.ait.dat215.project.Product;

import java.util.List;

public class AllProductsProductFilter implements ProductFilter {

    @Override
    public List<Product> filterProducts(List<Product> products) {
        // Return whole list
        return products;
    }

    @Override
    public String getName() {
        return "Hela sortimentet";
    }
}
