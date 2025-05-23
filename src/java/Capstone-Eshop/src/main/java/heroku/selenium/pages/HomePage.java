package heroku.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {  // or any page class representing the logged-in landing page

    private WebDriver driver;
    private WebDriverWait wait;

    // Locator for the dropdown toggle/button (adjust as per your actual HTML)
    private By userDropdown = By.id("userDropdown"); // example id
    // Locator for the "Admin" option inside dropdown (adjust accordingly)
    private By adminOption = By.xpath("//a[text()='Admin']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectAdminFromDropdown() {
        // Wait and click dropdown to open
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(userDropdown));
        dropdown.click();

        // Wait and click the Admin option
        WebElement adminLink = wait.until(ExpectedConditions.elementToBeClickable(adminOption));
        adminLink.click();
    }
}
