package heroku.operations;

public interface LoginPageOperations {
    void enterEmail(String email);
    void enterPassword(String password);
    void clickLogin();
    boolean isLoginSuccessful();
    String getLoginErrorMessage();
}
