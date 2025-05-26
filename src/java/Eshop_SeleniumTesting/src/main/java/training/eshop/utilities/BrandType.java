/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class represents brand type filter functionality for the eShop.
 *  It provides methods to select a brand type by name and to retrieve the list of available brand type options.
 * =======================================================================================
 */

package training.eshop.utilities;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BrandType {

    // List of brand type option elements available in the UI
    public List<WebElement> options;

    /**
     * Constructor to initialize BrandType with a list of WebElement options.
     * @param options list of brand type label elements
     */
    public BrandType(List<WebElement> options) {
        this.options = options;
    }

    /**
     * Selects a brand type checkbox or label by matching the brand type name.
     * @param brandLabelElements list of brand type label WebElements
     * @param brandName brand type name to select (case insensitive)
     */
    public static void selectBrandByName(List<WebElement> brandLabelElements, String brandName) {
        for (WebElement label : brandLabelElements) {
            if (label.getText().trim().equalsIgnoreCase(brandName)) {
                label.click();
                break;
            }
        }
    }

    /**
     * Returns a list of brand type names extracted from the provided WebElements.
     * @param options list of brand type label WebElements
     * @return list of brand type names as strings
     */
    public static List<String> getOptions(List<WebElement> options) {
        List<String> brandNames = new ArrayList<>();
        for (WebElement label : options) {
            brandNames.add(label.getText().trim());
        }
        return brandNames;
    }
}
