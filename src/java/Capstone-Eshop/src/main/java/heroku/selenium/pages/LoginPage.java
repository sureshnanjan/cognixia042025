package heroku.selenium.pages;

import heroku.operations.LoginPageOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static heroku.utilities.WebElementInteractions.*;

public class LoginPage implements LoginPageOperations {

    private WebDriver driver;

    // Locators (based on your provided XPaths)
    private final By initialLoginBtnLocator = By.xpath("/html/body/div/header/div/article/section[2]/div/section/div/a");
    private final By usernameLocator = By.xpath("//*[@id=\"Input_Email\"]");
    private final By passwordLocator = By.xpath("//*[@id=\"Input_Password\"]");
    private final By loginBtnLocator = By.xpath("/html/body/div/div/div/div/section/form/div[5]/button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://35.193.6.1:5106/");
    }

    @Override
    public void goToLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(initialLoginBtnLocator));
        loginBtn.click();
    }

    @Override
    public void enterUsername(String username) {
        sendKeysUtil(findElementUtil(usernameLocator, driver), username);
    }

    @Override
    public void enterPassword(String password) {
        sendKeysUtil(findElementUtil(passwordLocator, driver), password);
    }

    @Override
    public void clickLogin() {
        clickElementUtil(findElementUtil(loginBtnLocator, driver));
    }

    @Override
    public void login(String username, String password) {
        goToLoginPage();
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}

