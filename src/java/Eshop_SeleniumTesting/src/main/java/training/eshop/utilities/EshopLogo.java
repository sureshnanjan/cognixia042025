/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class represents the eShop logo with its image URL and description.
 *  Provides getter methods to access these properties.
 * =======================================================================================
 */

package training.eshop.utilities;

public class EshopLogo {

    // URL of the logo image
    private String image_url;

    // Description of the logo
    private String description;

    /**
     * Constructor to initialize EshopLogo with image URL and description.
     * @param image_url URL of the logo image
     * @param description Description of the logo
     */
    public EshopLogo(String image_url, String description) {
        this.image_url = image_url;
        this.description = description;
    }

    /**
     * Gets the URL of the logo image.
     * @return image URL as String
     */
    public String getImageUrl(){
        return image_url;
    }

    /**
     * Gets the description of the logo.
     * @return description as String
     */
    public String getImageDescription(){
        return description;
    }
}
