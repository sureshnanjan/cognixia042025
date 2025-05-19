package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class for common WebElement interactions in Selenium WebDriver.
 */
public class WebElementInteractions {

    /**
     * Sends the specified text to the input element located by the given locator.
     * Clears any existing text before sending.
     *
     * @param locator the By locator used to find the input element
     * @param text    the text to send to the element
     * @param driver  the WebDriver instance
     */
    public static void sendKeysToElement(By locator, String text, WebDriver driver) {
        try {
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
            System.out.println("Text sent to element: " + text);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("Failed to send text to element: " + e.getMessage());
        }
    }

    /**
     * Checks if the WebElement located by the given locator is visible on the page.
     *
     * @param locator the By locator used to find the element
     * @param driver  the WebDriver instance
     * @return true if the element is displayed, false otherwise
     */
    public static boolean isElementDisplayed(By locator, WebDriver driver) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found for visibility check.");
            return false;
        }
    }

    /**
     * Retrieves the value of the specified attribute from the element located by the given locator.
     *
     * @param locator   the By locator used to find the element
     * @param attribute the name of the attribute to retrieve
     * @param driver    the WebDriver instance
     * @return the value of the attribute, or null if element is not found
     */
    public static String getAttributeValue(By locator, String attribute, WebDriver driver) {
        try {
            WebElement element = driver.findElement(locator);
            return element.getAttribute(attribute);
        } catch (NoSuchElementException e) {
            System.out.println("Failed to get attribute '" + attribute + "': " + e.getMessage());
            return null;
        }
    }

    /**
     * Waits explicitly for the element located by the given locator to become visible on the page.
     *
     * @param locator        the By locator of the element
     * @param driver         the WebDriver instance
     * @param timeoutSeconds the maximum time to wait in seconds
     * @return the visible WebElement if found within the timeout
     */
    public static WebElement waitForElementVisible(By locator, WebDriver driver, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            System.out.println("Element not visible after waiting for " + timeoutSeconds + " seconds: " + locator);
            return null;
        }
    }
}
