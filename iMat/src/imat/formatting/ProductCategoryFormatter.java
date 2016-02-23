package imat.formatting;

import se.chalmers.ait.dat215.project.ProductCategory;

public class ProductCategoryFormatter {

    public static String getFormattedName(ProductCategory category) {

        switch (category) {
            case POD: return "Baljväxter";
            case BREAD: return "Bröd";
            case CITRUS_FRUIT: return "Citrusfrukter";
            case HOT_DRINKS: return "Varma drycker";
            case COLD_DRINKS: return "Kalla drycker";
            case EXOTIC_FRUIT: return "Exotiska frukter";
            case FISH: return "Fisk";
            case VEGETABLE_FRUIT: return "Grönsaksfrukter";
            case CABBAGE: return "Kål";
            case MEAT: return "Kött";
            case DAIRIES: return "Mejeriprodukter";
            case MELONS: return "Meloner";
            case FLOUR_SUGAR_SALT: return "Mjöl/socker/salt";
            case PASTA: return "Pasta";
            case POTATO_RICE: return "Potatis/ris";
            case ROOT_VEGETABLE: return "Rotfrukter";
            case FRUIT: return "Stenfrukter";
            case SWEET: return "Sötsaker";
            case HERB: return "Kryddor";
            case BERRY: return "Bär";
            case NUTS_AND_SEEDS: return "Nötter/frön";
            default:
                System.err.println("Unknown product category " + category + " found. No formatting available.");
                return "UNKNOWN_PRODUCT_CATEGORY";
        }

    }

}
