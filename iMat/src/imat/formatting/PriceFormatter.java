package imat.formatting;

import se.chalmers.ait.dat215.project.Product;

import java.text.DecimalFormat;

public class PriceFormatter {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");

    public static String getFormattedPrice(Product product) {
        double price = product.getPrice();
        String unit = product.getUnit();

        return DECIMAL_FORMAT.format(price) + " " + unit;
    }

}
