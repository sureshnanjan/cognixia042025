package heroku.selenium.pages;
import heroku.operations.ForgotPasswordPageOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static heroku.utilities.WebElementInteractions.*;

public class ForgotPasswordPage implements ForgotPasswordPageOperations {

    private WebDriver driver;

    // Locators (based on your XPaths)
    private final By initialLoginBtnLocator = By.xpath("/html/body/div/header/div/article/section[2]/div/section/div/a");
    private final By forgotPasswordLinkLocator = By.xpath("/html/body/div/div/div/div/section/form/div[6]/p[1]/a");
    private final By emailInputLocator = By.xpath("//*[@id=\"Input_Email\"]");
    private final By resetPasswordBtnLocator = By.xpath("/html/body/div/div/div/form/button");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://34.46.74.231:5106/"); // Use correct live URL
    }

    @Override
    public void goToHomePage() {
        this.driver.get("http://34.46.74.231:5106/");
    }

    @Override
    public void goToLoginPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(initialLoginBtnLocator));
        loginBtn.click();
    }

    @Override
    public void clickForgotPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement forgotLink = wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLinkLocator));
        forgotLink.click();
    }

    @Override
    public void enterEmail(String email) {
        sendKeysUtil(findElementUtil(emailInputLocator, driver), email);
    }

    @Override
    public void clickResetPassword() {
        clickElementUtil(findElementUtil(resetPasswordBtnLocator, driver));
    }

    @Override
    public void resetPasswordFlow(String email) {
        goToLoginPage();
        clickForgotPassword();
        enterEmail(email);
        clickResetPassword();
    }
}

