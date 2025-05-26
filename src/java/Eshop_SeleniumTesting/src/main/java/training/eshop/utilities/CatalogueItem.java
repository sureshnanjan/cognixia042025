/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class represents a catalogue item in the eShop.
 *  It holds details about the item such as image URL, name, price, and visibility of key elements.
 *  Also provides methods to add the item to cart, validate display, and match product name.
 * =======================================================================================
 */

package training.eshop.utilities;

public class CatalogueItem {

    // URL of the item's image
    public String image_url;

    // Name of the catalogue item
    public String item_name;

    // Price of the item
    public float price;

    // Visibility flags for UI elements of the item
    public boolean imageVisible;
    public boolean nameVisible;
    public boolean priceVisible;
    public boolean addToCartBtnVisible;

    /**
     * Simulates adding the item to the cart.
     */
    public void addToCart() {
        System.out.println("Added to cart");
    }

    /**
     * Validates that all main elements of the catalogue item are displayed.
     * @return true if image, name, price, and add to cart button are visible; false otherwise
     */
    public boolean validateItemDisplayed() {
        return imageVisible && nameVisible && priceVisible && addToCartBtnVisible;
    }

    /**
     * Checks if the product name matches the expected name (case insensitive).
     * @param expectedName expected product name to match
     * @return true if names match ignoring case, false otherwise
     */
    public boolean matchProductName(String expectedName) {
        return item_name != null && item_name.equalsIgnoreCase(expectedName);
    }
}
