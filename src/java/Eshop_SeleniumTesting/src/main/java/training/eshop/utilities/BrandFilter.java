/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class represents brand filter functionality for the eShop.
 *  It provides methods to select a brand by name and to get the list of available brand options.
 * =======================================================================================
 */

package training.eshop.utilities;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BrandFilter {

    // List of brand option elements available in the UI
    public List<WebElement> options;

    /**
     * Constructor to initialize BrandFilter with a list of WebElement options.
     * @param options list of brand label elements
     */
    public BrandFilter(List<WebElement> options) {
        this.options = options;
    }

    /**
     * Selects a brand checkbox or label by matching the brand name.
     * @param brandLabelElements list of brand label WebElements
     * @param brandName brand name to select (case insensitive)
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
     * Returns a list of brand names extracted from the provided WebElements.
     * @param options list of brand label WebElements
     * @return list of brand names as strings
     */
    public static List<String> getOptions(List<WebElement> options) {
        List<String> brandNames = new ArrayList<>();
        for (WebElement label : options) {
            brandNames.add(label.getText().trim());
        }
        return brandNames;
    }
}
