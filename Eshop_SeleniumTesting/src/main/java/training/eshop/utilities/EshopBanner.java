/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class represents an eShop Banner with its image URL and description.
 *  It provides getter methods to access banner properties.
 * =======================================================================================
 */

package training.eshop.utilities;

public class EshopBanner {

    // URL of the banner image
    private String image_url;

    // Description text for the banner
    private String description;

    /**
     * Constructor to initialize EshopBanner with image URL.
     * @param image_url URL of the banner image
     */
    public EshopBanner(String image_url) {
        this.image_url = image_url;
    }

    /**
     * Gets the URL of the banner image.
     * @return image URL as String
     */
    public String getImageUrl(){
        return image_url;
    }

    // Optional: Add getter/setter for description if needed in future
}
