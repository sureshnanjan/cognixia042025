/*
 * Copyright (c) 2025 Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 * All rights reserved.
 *
 * This interface defines the operations related to the Basket Page in the eShop application.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package training.eshop.operations;

/**
 * Interface for managing operations on the Basket Page.
 * Extends base EshopOperations interface.
 */
public interface BasketPageOperations extends EshopOperations {

    /**
     * Retrieves the title of the Basket Page.
     *
     * @return the page title
     */
    String getTitle();

    /**
     * Adds a specified item to the basket.
     *
     * @param itemName the name of the item to add
     */
    String addItemToBasket(String itemName);

    /**
     * Verifies if the item count updates correctly when modified.
     */
    String checkUpdateItemCount(); // Better naming for clarity

    /**
     * Verifies if the total price updates accurately after item count changes.
     */
    String checkPriceUpdate(); // Clearer and more concise

    /**
     * Proceeds with the checkout process from the basket.
     */
    boolean proceedToCheckout(); // Action-oriented method name
}
