package heroku.operations;
public interface LoginPageOperations {
    /**
     * Clicks the initial login button on the landing page to go to the login form.
     */
    void goToLoginPage();
    /**
     * Enters the username (email) into the login form.
     * @param username the email/username to log in with
     */
    void enterUsername(String username);
    /**
     * Enters the password into the login form.
     * @param password the password to log in with
     */
    void enterPassword(String password);
    /**
     * Clicks the login button to submit the login form.
     */
    void clickLogin();
    /**
     * Performs the full login sequence:
     * visit → click login → fill credentials → submit.
     * @param username the email/username
     * @param password the password
     */
    void login(String username, String password);
}

