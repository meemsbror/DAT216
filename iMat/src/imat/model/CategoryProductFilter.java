package imat.model;

import imat.formatting.ProductCategoryFormatter;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ProductCategory;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryProductFilter implements ProductFilter {

    private final ProductCategory productCategory;

    public CategoryProductFilter(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public List<Product> filterProducts(List<Product> products) {
        return products.stream().filter(p -> p.getCategory() == this.productCategory).collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return ProductCategoryFormatter.getFormattedName(this.productCategory);
    }
}
