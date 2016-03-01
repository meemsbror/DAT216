package imat.filter;

import se.chalmers.ait.dat215.project.Product;

import java.util.List;

/**
 *
 * Defines a filter for products. A filter is either a ProductCategory OR favorites OR all.
 *
 */
public interface ProductFilter {

    /**
     *
     * Filters a list of products into a new list.
     *
     * @param products products to filter
     * @return filtered list
     */
    public List<Product> filterProducts(List<Product> products);

    /**
     *
     * Returns the display name of the product filter.
     *
     * @return the name of the filter
     */
    public String getName();
}
