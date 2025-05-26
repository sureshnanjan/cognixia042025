/*
 * Copyright (c) 2025 Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 * All rights reserved.
 *
 * This interface defines operations related to the Forgot Password functionality
 * in the eShop application.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package training.eshop.operations;

/**
 * Interface for handling Forgot Password operations in the eShop application.
 */
public interface ForgotPasswordOperations {

    /**
     * Retrieves the title of the Forgot Password page.
     *
     * @return the page title
     */
    String getTitle();

    /**
     * Triggers the change password workflow using the user's registered email.
     *
     * @param email the registered email address of the user
     */
    void ChangePassword(String email);
}
