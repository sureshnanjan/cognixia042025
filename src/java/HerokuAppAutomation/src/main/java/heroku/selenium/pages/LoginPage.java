package heroku.selenium.pages;

import heroku.operations.LoginPageOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WebElementInteractions;

public class LoginPage implements LoginPageOperations {

    private WebDriver driver;

    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By loginButton = By.xpath("//input[@value='Log in']");
    private By loginErrorMessage = By.id("login-error"); // assuming an id, else adjust

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Override
    public void enterEmail(String email) {
        WebElement emailElem = WebElementInteractions.findElementUtil(emailInput, driver);
        WebElementInteractions.sendKeysUtil(emailElem, email);
    }

    @Override
    public void enterPassword(String password) {
        WebElement pwdElem = WebElementInteractions.findElementUtil(passwordInput, driver);
        WebElementInteractions.sendKeysUtil(pwdElem, password);
    }

    @Override
    public void clickLogin() {
        WebElement loginBtn = WebElementInteractions.findElementUtil(loginButton, driver);
        WebElementInteractions.clickElementUtil(loginBtn);
    }

    @Override
    public boolean isLoginSuccessful() {
        // For example, check if dashboard heading is visible or URL changed
        return driver.getCurrentUrl().contains("myshuttledev");
    }

    @Override
    public String getLoginErrorMessage() {
        WebElement errElem = WebElementInteractions.findElementUtil(loginErrorMessage, driver);
        return WebElementInteractions.getTextUtil(errElem);
    }
}