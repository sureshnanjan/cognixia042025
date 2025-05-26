/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class represents an eShop Cart icon with its image URL, logo color, and item count.
 *  It provides getters to access these properties.
 * =======================================================================================
 */

package training.eshop.utilities;

import java.awt.Color;

public class EshopCart {

    // URL of the cart image/icon
    private String image_url;

    // Color of the cart logo (optional, can be null)
    private Color logo_color;

    // Number of items in the cart
    private int count;

    /**
     * Constructor to initialize EshopCart with image URL and count.
     * @param image_url URL of the cart image/icon
     * @param count Number of items in the cart
     */
    public EshopCart(String image_url, int count) {
        this.image_url = image_url;
        this.count = count;
        // logo_color is not set via constructor; can add setter if needed
    }

    /**
     * Gets the URL of the cart image/icon.
     * @return image URL as String
     */
    public String getImageUrl(){
        return image_url;
    }

    /**
     * Gets the count of items in the cart.
     * @return item count as int
     */
    public int getCartCount(){
        return count;
    }

    // Optionally, add getter/setter for logo_color if needed
}
