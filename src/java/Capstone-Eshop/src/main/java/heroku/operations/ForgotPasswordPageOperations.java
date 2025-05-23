package heroku.operations;

/**
 * Operations for navigating to the login page and handling the "Forgot Your Password?" flow.
 */
public interface ForgotPasswordPageOperations {

    /**
     * Navigates to the website's homepage.
     */
    void goToHomePage();

    /**
     * Clicks the login link or button to navigate to the login form.
     * XPath: /html/body/div/header/div/article/section[2]/div/section/div/a
     */
    void goToLoginPage();

    /**
     * Clicks the "Forgot your password?" link on the login form.
     * XPath: /html/body/div/div/div/div/section/form/div[6]/p[1]/a
     */
    void clickForgotPassword();

    /**
     * Enters an email address into the "Forgot Password" input field.
     * XPath for email input: //*[@id="Input_Email"]
     * @param email the email address to reset password for
     */
    void enterEmail(String email);

    /**
     * Clicks the reset password button to submit the form.
     * XPath: /html/body/div/div/div/form/button
     */
    void clickResetPassword();

    /**
     * Performs the full forgot password flow: visit site → login page → forgot password →
     * enter email → submit.
     * @param email the email to reset password for
     */
    void resetPasswordFlow(String email);
}

