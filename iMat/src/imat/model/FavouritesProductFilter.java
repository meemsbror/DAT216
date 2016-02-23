package imat.model;

import se.chalmers.ait.dat215.project.Product;

import java.util.List;
import java.util.stream.Collectors;

public class FavouritesProductFilter implements ProductFilter {

    @Override
    public List<Product> filterProducts(List<Product> products) {
        return products.stream().filter(this::isFavourite).collect(Collectors.toList());
    }

    private boolean isFavourite(Product product) {
        // TODO: Implement!
        return false;
    }

    @Override
    public String getName() {
        return "Favoriter";
    }
}
