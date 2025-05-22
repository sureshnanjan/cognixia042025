package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

/**
 * Utility class for common WebElement interactions using Selenium WebDriver.
 */
public class WebElementInteractions {

    /**
     * Navigates the browser to the specified URL.
     *
     * @param url the URL to navigate to
     */
    public static void navigatetoUrlUtil(String url) {
        // Method implementation to be added
    }

    /**
     * Finds and returns a WebElement using the given locator.
     *
     * @param locator the By locator to find the element
     * @param driver  the WebDriver instance
     * @return the WebElement if found, otherwise null
     */
    public static WebElement findElementUtil(By locator, WebDriver driver) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException ex) {
            System.out.println("Interaction Not Possible");
            return null;
        }
    }

    /**
     * Retrieves the text from the specified WebElement.
     *
     * @param element the WebElement to get text from
     * @return the text of the element
     */
    public static String getTextUtil(WebElement element) {
        System.out.println("Getting Text from the element");
        return element.getText();
    }

    /**
     * Attempts to click on the specified WebElement.
     *
     * @param element the WebElement to click
     */
    public static void clickElementUtil(WebElement element) {
        try {
            element.click();
        } catch (StaleElementReferenceException ex) {
            System.out.println("Element Click not happened");
        }
    }

    /**
     * Performs authentication on a website using the provided method and credentials.
     *
     * @param uname    the username
     * @param password the password
     * @param auth     the authentication method (Basic or Digest)
     * @param url      the URL to navigate to
     * @param driver   the WebDriver instance
     */
    public static void AuthenticateUtil(String uname, String password, AuthMethods auth, String url, WebDriver driver) {
        switch (auth) {
            case Basic:
                driver.get(String.format("https://%s:%s@%s", uname, password, url));
                break;
            case Digest:
                // Digest authentication not implemented
                break;
        }
    }

    /**
     * Performs a right-click (context click) on the given WebElement.
     *
     * @param element the WebElement to right-click
     * @param driver  the WebDriver instance
     */
    public static void RightClickUtil(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
    }

    /**
     * Retrieves and accepts the current browser alert.
     *
     * @param driver the WebDriver instance
     * @return the text of the alert
     */
    public static String getAlertText(WebDriver driver) {
        Alert alt = driver.switchTo().alert();
        String result = alt.getText();
        alt.accept();
        return result;
    }

    /**
     * Finds and clicks a link by its visible text.
     *
     * @param linkname the visible text of the link
     * @param driver   the WebDriver instance
     */
    public static void navigateToLinkUtil(String linkname, WebDriver driver) {
        clickElementUtil(findElementUtil(By.linkText(linkname), driver));
    }

    /**
     * Submits a form using the specified WebElement.
     *
     * @param element the WebElement (typically inside a form) to submit
     */
    public static void submitElementUtil(WebElement element) {
        try {
            element.submit();
        } catch (NoSuchElementException | ElementNotInteractableException ex) {
            System.out.println("Element Submit not possible: " + ex.getMessage());
        }
    }
}
