/*
 * Copyright (c) 2025 Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 * All rights reserved.
 *
 * This interface defines operations related to user registration in the eShop application.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package training.eshop.operations;

/**
 * Interface for handling user registration functionality in the eShop application.
 */
public interface RegisterPageOperations extends EshopOperations {

    /**
     * Retrieves the title of the Registration Page.
     *
     * @return the page title
     */
    String getTitle();

    /**
     * Performs user registration using provided credentials.
     *
     * @param username         the desired username or email
     * @param password         the user's chosen password
     * @param confirmPassword  confirmation of the password for validation
     */
    void doRegister(String username, String password, String confirmPassword);
}
