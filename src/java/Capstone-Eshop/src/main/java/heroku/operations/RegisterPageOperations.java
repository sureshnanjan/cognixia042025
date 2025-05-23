package heroku.operations;
public interface RegisterPageOperations {
    /**
     * Clicks the initial login button on the landing page to go to the login screen.
     */
    void goToLoginPage();
    /**
     * Clicks the "Register as a new user" link to open the registration form.
     */
    void goToRegisterForm();
    /**
     * Enters the email address into the registration form.
     * @param email the email to register with
     */
    void enterEmail(String email);
    /**
     * Enters the password into the registration form.
     * @param password the password to register with
     */
    void enterPassword(String password);
    /**
     * Enters the confirmation password into the registration form.
     * @param confirmPassword the confirmation of the password
     */
    void enterConfirmPassword(String confirmPassword);
    /**
     * Clicks the final Register button to submit the form.
     */
    void clickRegister();
    /**
     * Performs the full registration sequence:
     * visit → click login → click register → fill form → submit.
     * @param email user's email
     * @param password password
     * @param confirmPassword confirm password
     */
    void register(String email, String password, String confirmPassword);
}

