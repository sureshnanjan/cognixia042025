/*
 * Copyright (c) 2025 Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 * All rights reserved.
 *
 * This interface defines operations related to order management in the eShop application.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package training.eshop.operations;

/**
 * Interface for handling order-related operations in the eShop application.
 */
public interface OrderOperations {

    /**
     * Retrieves the title of the Order Page.
     *
     * @return the page title
     */
    String getTitle();

    /**
     * Gets the total number of orders placed by the user.
     *
     * @return the order count
     */
    int getOrderCount();

    /**
     * Validates the details of a specific order.
     */
    void checkOrderDetails();
}
