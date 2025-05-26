/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class holds the account details such as phone number, email, and two-factor authentication status.
 *  Provides getter methods to access these details.
 * =======================================================================================
 */

package training.eshop.utilities;

public class AccountDetails {

    // Phone number associated with the account
    private String phoneNumber;

    // Email address associated with the account
    private String email;

    // Status of two-factor authentication (e.g., Enabled/Disabled)
    private String twoFactorStatus;

    /**
     * Constructor to initialize account details.
     * @param phoneNumber phone number string
     * @param email email string
     * @param twoFactorStatus two-factor authentication status string
     */
    public AccountDetails(String phoneNumber, String email, String twoFactorStatus) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.twoFactorStatus = twoFactorStatus;
    }

    /**
     * Returns the phone number of the account.
     * @return phone number string
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the email address of the account.
     * @return email string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the two-factor authentication status of the account.
     * @return two-factor status string
     */
    public String getTwoFactorStatus() {
        return twoFactorStatus;
    }
}
