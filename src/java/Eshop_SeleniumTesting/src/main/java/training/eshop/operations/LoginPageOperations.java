/*
 * Copyright (c) 2025 Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 * All rights reserved.
 *
 * This interface defines operations related to the Login Page of the eShop application.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package training.eshop.operations;

/**
 * Interface for handling Login and Logout operations in the eShop application.
 * Extends the EshopOperations interface for standardized operation handling.
 */
public interface LoginPageOperations extends EshopOperations {

    /**
     * Retrieves the title of the Login Page.
     *
     * @return the page title
     */
    String getTitle();

    /**
     * Performs login using provided user credentials.
     *
     * @param username    the username or email for login
     * @param password    the password for authentication
     * @param rememberMe  flag to indicate if 'Remember Me' should be enabled
     */
    void doLogin(String username, String password, boolean rememberMe);

    /**
     * Performs logout operation (simulated here using same credentials).
     *
     * @param username    the username or email for logout reference
     * @param password    the password used during login
     * @param rememberMe  original rememberMe flag to verify state before logout
     */
    void doLogout(String username, String password, boolean rememberMe);
}
