package heroku.selenium.pages;

import heroku.operations.CheckoutPageOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static heroku.utilities.WebElementInteractions.*;

public class CheckoutPage implements CheckoutPageOperations {

    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver.get("http://35.193.6.1:5106/Basket");
    }

    // -------------------- Locators --------------------

    private final By checkoutBtn = By.xpath("/html/body/div/div/form/div/div[3]/section[2]/a");
    private final By payNowBtn = By.xpath("/html/body/div/div/form/div/div[3]/section[2]/input");
    private final By thankYouMessage = By.xpath("/html/body/div/div/h1");

    // -------------------- Checkout Methods --------------------

    @Override
    public void clickCheckoutButton() {
        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        scrollToElement(checkout, driver);
        clickElementUtil(checkout);
    }

    @Override
    public void clickPayNowButton() {
        WebElement payNow = wait.until(ExpectedConditions.elementToBeClickable(payNowBtn));
        scrollToElement(payNow, driver);
        clickElementUtil(payNow);
    }

    @Override
    public void completePurchase() {
        clickCheckoutButton();
        clickPayNowButton();
    }

    @Override
    public boolean isPurchaseSuccessful() {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(thankYouMessage));
            System.out.println(message.getText());
            return message.getText().contains("Thanks for your Order!");
        } catch (Exception e) {
            return false;
        }
    }
}
