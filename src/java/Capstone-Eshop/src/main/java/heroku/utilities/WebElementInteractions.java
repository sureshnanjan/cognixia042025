package heroku.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Utility class for interacting with WebElements safely.
 */
public class WebElementInteractions {

    /**
     * Finds a WebElement using the provided locator and WebDriver.
     *
     * @param locator the By locator of the element
     * @param driver  the WebDriver instance
     * @return the WebElement if found, otherwise null
     */
    public static WebElement findElementUtil(By locator, WebDriver driver) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException ex) {
            System.out.println("Element not found: " + locator);
            return null;
        }
    }
    public static WebDriver createDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Gets the visible text from a WebElement.
     *
     * @param element the WebElement
     * @return the text content of the element, or null if element is null
     */
    public static String getTextUtil(WebElement element) {
        if (element != null) {
            return element.getText();
        }
        return null;
    }

    /**
     * Sends input text to a WebElement like a text field.
     *
     * @param element the WebElement
     * @param text    the text to send
     */
    public static void sendKeysUtil(WebElement element, String text) {
        if (element != null) {
            element.clear();
            element.sendKeys(text);
        } else {
            System.out.println("Cannot send keys. Element is null.");
        }
    }

    /**
     * Clicks on a WebElement like a button or link.
     *
     * @param element the WebElement to click
     */
    public static void clickElementUtil(WebElement element) {
        if (element != null) {
            element.click();
        } else {
            System.out.println("Cannot click. Element is null.");
        }
    }

    /**
     * Clears the content of a WebElement (e.g., input field).
     *
     * @param element the WebElement to clear
     */
    public static void clearUtil(WebElement element) {
        if (element != null) {
            element.clear();
        } else {
            System.out.println("Cannot clear. Element is null.");
        }
    }
    /**
     * Scrolls to the given WebElement using JavaScript.
     *
     * @param driver the WebDriver instance
     * @param element the WebElement to scroll to
     */
    public static void scrollToElement(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public static boolean isElementPresent(By locator, WebDriver driver) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }



}
