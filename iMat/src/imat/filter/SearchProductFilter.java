package imat.filter;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;

import java.util.List;

public class SearchProductFilter implements ProductFilter {

    private String searchQuery;

    public SearchProductFilter(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public List<Product> filterProducts(List<Product> products) {
        return IMatDataHandler.getInstance().findProducts(searchQuery);
    }

    @Override
    public String getName() {
        return "Sökning: \"" + searchQuery + "\"";
    }
}
