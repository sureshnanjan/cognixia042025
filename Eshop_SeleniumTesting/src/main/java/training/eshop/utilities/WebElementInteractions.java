/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  Utility class containing reusable Selenium WebDriver interaction methods for the eShop project.
 *  Includes methods for navigation, element finding, text input, clicking, scrolling, and waits.
 * =======================================================================================
 */

package training.eshop.utilities;

import org.openqa.selenium.*;

import java.util.Collections;
import java.util.List;

public class WebElementInteractions {

    /**
     * Navigates the browser to the specified URL.
     *
     * @param url    The target URL to navigate to.
     * @param driver The WebDriver instance controlling the browser.
     */
    public static void navigatetoUrlUtil(String url, WebDriver driver){
        System.out.println("Starting Navigating");
        driver.get(url);
    }

    /**
     * Finds a single WebElement by the given locator.
     *
     * @param locator The By locator to find the element.
     * @param driver  The WebDriver instance.
     * @return The found WebElement, or null if not found.
     */
    public static WebElement findElementUtil(By locator, WebDriver driver){
        try {
            return driver.findElement(locator);
        } catch(NoSuchElementException ex) {
            System.out.println("Interaction Not Possible: Element not found for locator: " + locator);
            return null;
        }
    }

    /**
     * Finds all WebElements matching the given locator.
     *
     * @param locator The By locator to find elements.
     * @param driver  The WebDriver instance.
     * @return List of found WebElements, empty if none found.
     */
    public static List<WebElement> findElementsUtil(By locator, WebDriver driver) {
        try {
            List<WebElement> elements = driver.findElements(locator);
            if (elements.isEmpty()) {
                System.out.println("No elements found for locator: " + locator);
            }
            return elements;
        } catch (NoSuchElementException ex) {
            System.out.println("Interaction not possible - NoSuchElementException for: " + locator);
            return Collections.emptyList();
        }
    }

    /**
     * Attempts to retrieve the page title by searching for h1, h2, or h3 tags in that order.
     *
     * @param driver The WebDriver instance.
     * @return Text content of the first found heading element or the exception message if none found.
     */
    public static String getCurrentPageTitle(WebDriver driver){
        try {
            return driver.findElement(By.tagName("h1")).getText();
        } catch (NoSuchElementException e) {
            try {
                return driver.findElement(By.tagName("h2")).getText();
            } catch (NoSuchElementException e2) {
                try {
                    return driver.findElement(By.tagName("h3")).getText();
                } catch (NoSuchElementException e3) {
                    return e3.getMessage();
                }
            }
        }
    }

    /**
     * Gets the current URL loaded in the browser.
     *
     * @param driver The WebDriver instance.
     * @return The current URL as a String, or null if stale element exception occurs.
     */
    public static String getCurrentUrlUtil(WebDriver driver){
        try {
            return driver.getCurrentUrl();
        } catch (StaleElementReferenceException ex) {
            System.out.println("Element click not happened - StaleElementReferenceException");
            return null;
        }
    }

    /**
     * Gets the visible text of a WebElement.
     *
     * @param element The WebElement.
     * @return The text of the element or null if element is null.
     */
    public static String getTextUtil(WebElement element) {
        if (element != null) {
            return element.getText();
        }
        return null;
    }

    /**
     * Clears any existing text and sends the given text input to the WebElement.
     *
     * @param element The WebElement (e.g., input field).
     * @param text    The text to enter.
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
     * Clicks on the provided WebElement.
     *
     * @param element The WebElement to click.
     */
    public static void clickElementUtil(WebElement element) {
        if (element != null) {
            element.click();
        } else {
            System.out.println("Cannot click. Element is null.");
        }
    }

    /**
     * Clears the content of the given WebElement.
     *
     * @param element The WebElement to clear.
     */
    public static void clearUtil(WebElement element) {
        if (element != null) {
            element.clear();
        } else {
            System.out.println("Cannot clear. Element is null.");
        }
    }

    /**
     * Scrolls the browser viewport to bring the specified WebElement into view.
     *
     * @param element The WebElement to scroll to.
     * @param driver  The WebDriver instance.
     */
    public static void scrollToElement(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Checks whether the element specified by locator is present and visible on the page.
     *
     * @param locator The By locator.
     * @param driver  The WebDriver instance.
     * @return True if the element is present and displayed, false otherwise.
     */
    public static boolean isElementPresent(By locator, WebDriver driver) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Enters text into the specified WebElement, clearing existing text first.
     *
     * @param element The WebElement.
     * @param text    The text to enter.
     */
    public static void enterTextUtil(WebElement element, String text) {
        if (element != null && text != null) {
            element.clear();
            element.sendKeys(text);
        }
    }

    /**
     * Pauses execution for the specified number of milliseconds.
     *
     * @param milliseconds The time to sleep in milliseconds.
     */
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted during sleep.", e);
        }
    }

    /**
     * Scrolls the browser window by the specified x and y offset.
     *
     * @param x       The horizontal offset in pixels.
     * @param y       The vertical offset in pixels.
     * @param browser The WebDriver instance.
     */
    public static void scrollBy(int x, int y, WebDriver browser) {
        ((JavascriptExecutor) browser).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }
}
