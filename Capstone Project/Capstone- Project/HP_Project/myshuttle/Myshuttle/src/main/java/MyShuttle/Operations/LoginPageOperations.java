package MyShuttle.Operations;

public interface LoginPageOperations {
    void enterEmail(String email);

    void enterPassword(String password);

    void clickLogin();

    String getLoginPageTitle();
}

