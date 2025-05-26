package MyShuttle.Operations;


/**
 * Defines operations related to the login page.
 */
public interface LoginPageOperations {




    /**
     * Enters the user email.
     * @param email the user's email address
     */
    void enterEmail(String email);





    /**
     * Enters the user password.
     * @param password the user's password
     */
    void enterPassword(String password);





    /**
     * Clicks the login button.
     */
    void clickLogin();





    /**
     * Retrieves the title of the login page.
     * @return the login page title
     */
    String getLoginPageTitle();
}

