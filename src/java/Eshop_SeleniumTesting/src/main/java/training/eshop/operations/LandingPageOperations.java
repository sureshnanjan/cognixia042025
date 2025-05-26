/*
 * Copyright (c) 2025 Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 * All rights reserved.
 *
 * This interface defines operations related to the Landing Page of the eShop application.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package training.eshop.operations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import training.eshop.utilities.*;

import java.util.List;

/**
 * Interface for handling operations on the Landing Page of the eShop application.
 * Extends EshopOperations to maintain a unified structure across modules.
 */
public interface LandingPageOperations extends EshopOperations {

    /**
     * Retrieves the title of the Landing Page.
     *
     * @return the page title
     */
    String getTitle();

    /**
     * Fetches details of the eShop logo component.
     *
     * @return an object representing logo properties
     */
    EshopLogo getLogoDetails();

    /**
     * Navigates to the Login Page.
     *
     * @return a new instance of EshopOperations for the Login Page
     */
    EshopOperations enterLoginPage();

    /**
     * Navigates to the Basket Page.
     *
     */
    String openBasketAndGetMessage();

    /**
     * Retrieves banner details displayed on the Landing Page.
     *
     * @return banner component object
     */
    EshopBanner getBannerDetails();

    /**
     * Accesses the brand filter section.
     *
     * @return a BrandFilter object containing available filter options
     */
    BrandFilter getBrandFilter();

    /**
     * Accesses the type/category selector for brands.
     *
     * @return a BrandType object representing brand classification
     */
    BrandType getBrandType();

    /**
     * Retrieves pagination component details.
     *
     * @return a PageNavigation object for navigating through catalogue pages
     */
    PageNavigation getPaginationDetails();

    /**
     * Retrieves all items displayed in the product catalogue.
     *
     * @return list of catalogue items
     */
    List<CatalogueItem> getCatalogueItems();

    /**
     * Filters catalogue products based on user selection.
     *
     * @return a filtered CatalogueItem
     */
    CatalogueItem filterProducts();

    /**
     * Fetches footer section details.
     *
     * @return a Footer object containing footer components
     */
    Footer getFooter();
}
