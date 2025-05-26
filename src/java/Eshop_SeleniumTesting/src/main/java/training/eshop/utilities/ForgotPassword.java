/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class models the Forgot Password feature data, including the user email,
 *  whether the reset email was sent, and any status message related to the process.
 * =======================================================================================
 */

package training.eshop.utilities;

public class ForgotPassword {

    private String email;
    private boolean emailSent;
    private String message;

    /**
     * Constructor to initialize ForgotPassword details.
     * @param email the user's email address
     * @param emailSent indicates if the reset email was successfully sent
     * @param message status or error message related to the forgot password process
     */
    public ForgotPassword(String email, boolean emailSent, String message) {
        this.email = email;
        this.emailSent = emailSent;
        this.message = message;
    }

    /**
     * Get the email associated with the forgot password request.
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Check if the reset password email was sent successfully.
     * @return true if email was sent, false otherwise
     */
    public boolean isEmailSent() {
        return emailSent;
    }

    /**
     * Get the message related to the forgot password operation.
     * @return status or error message
     */
    public String getMessage() {
        return message;
    }
}
