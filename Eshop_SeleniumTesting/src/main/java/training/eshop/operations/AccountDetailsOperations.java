/*
 * Copyright (c) 2025 Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 * All rights reserved.
 *
 * This interface defines the operations related to user account details in the eShop application.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package training.eshop.operations;

/**
 * Interface for managing user account details and operations.
 */
public interface AccountDetailsOperations {

    /**
     * Gets the user's title (e.g., Mr., Ms., Dr.).
     *
     * @return the title of the user
     */
    String getTitle();

    /**
     * Sends a verification email to the user's registered email address.
     * Note: This method is expected to fail under current implementation/testing.
     */
    void SendVerificationEmail(); // Should fail

    /**
     * Retrieves the user's registered phone number.
     *
     * @return the phone number
     */
    String getPhoneNumber();

    /**
     * Updates the user's profile with a new phone number.
     *
     * @param phoneNumber the new phone number to be updated
     */
    void ChangeProfile(String phoneNumber);

    /**
     * Changes the user's password.
     *
     * @param currentPassword current password of the user
     * @param newPassword new password to be set
     * @param confirmPassword re-entered new password for confirmation
     */
    void ChangePassword(String currentPassword, String newPassword, String confirmPassword);

    /**
     * Initiates Two-Factor Authentication setup.
     * Note: This method is expected to fail under current implementation/testing.
     */
    void TwoFactorAuthentication(); // Should Fail

    String changePasswordAndReturnMessage(String currentPassword, String newPassword, String confirmPassword);
}
