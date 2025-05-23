package heroku.selenium.pages;
import heroku.operations.RegisterPageOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static heroku.utilities.WebElementInteractions.*;

public class RegisterPage implements RegisterPageOperations {

    private WebDriver driver;
    // Locators (based on your provided XPaths)
    private final By initialLoginBtnLocator = By.xpath("/html/body/div/header/div/article/section[2]/div/section/div/a");
    private final By registerLinkLocator = By.xpath("/html/body/div/div/div/div/section/form/div[6]/p[2]/a");
    private final By emailLocator = By.xpath("//*[@id=\"Input_Email\"]");
    private final By passwordLocator = By.xpath("//*[@id=\"Input_Password\"]");
    private final By confirmPasswordLocator = By.xpath("//*[@id=\"Input_ConfirmPassword\"]");
    private final By registerBtnLocator = By.xpath("/html/body/div/div/div/div/form/button");

    public RegisterPage(WebDriver driver) {
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
    public void goToRegisterForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement registerLink = wait.until(ExpectedConditions.elementToBeClickable(registerLinkLocator));
        registerLink.click();
    }

    @Override
    public void enterEmail(String email) {
        sendKeysUtil(findElementUtil(emailLocator, driver), email);
    }

    @Override
    public void enterPassword(String password) {
        sendKeysUtil(findElementUtil(passwordLocator, driver), password);
    }

    @Override
    public void enterConfirmPassword(String confirmPassword) {
        sendKeysUtil(findElementUtil(confirmPasswordLocator, driver), confirmPassword);
    }

    @Override
    public void clickRegister() {
        clickElementUtil(findElementUtil(registerBtnLocator, driver));
    }

    @Override
    public void register(String email, String password, String confirmPassword) {
        goToLoginPage();
        goToRegisterForm();
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickRegister();
    }
}

