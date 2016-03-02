package imat.formatting;

import se.chalmers.ait.dat215.project.Product;

import java.text.DecimalFormat;

public class PriceFormatter {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public static String getFormattedPrice(Product product) {
        return getFormattedPrice(product, 1.0);
    }

    public static String getFormattedPrice(Product product, double quantity) {
        return getFormattedPriceWithoutUnit(product, quantity) + product.getUnit();
    }

    public static String getFormattedPriceWithoutUnit(Product product, double quantity) {
        double price = product.getPrice() * quantity;
        return DECIMAL_FORMAT.format(price);
    }
}
