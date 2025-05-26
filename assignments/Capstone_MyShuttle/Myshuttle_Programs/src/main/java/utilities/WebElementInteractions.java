package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.util.ArrayList;
import java.util.List;

public class WebElementInteractions {



    /**
     * Safely finds a web element using the specified locator.
     * Returns null if the element is not found.
     *
     * @param locator the By locator used to find the element
     * @param driver  the WebDriver instance
     * @return the found WebElement, or null if not found
     */
    public static WebElement findElementUtil(By locator, WebDriver driver){
        try{
            return driver.findElement(locator);

        }catch(NoSuchElementException ex){
            System.out.println("Interaction Not Possible");
            return  null;
        }
    }





    /**
     * Retrieves the visible text from the given web element.
     *
     * @param element the WebElement from which to retrieve text
     * @return the text content of the element
     */
    public static String getTextUtil(WebElement element){
        System.out.println("Getting Text from the element");
        return element.getText();
    }






    /**
     * Performs a right-click (context click) on the specified web element.
     *
     * @param element the WebElement to right-click on
     * @param driver  the WebDriver instance used to perform the action
     */
    public static void RightClickUtil(WebElement element, WebDriver driver){
        Actions actions = new Actions(driver);
        actions.contextClick(element);
        actions.perform();

    }






    /**
     * Retrieves the text from the currently displayed alert and accepts it.
     *
     * @param driver the WebDriver instance used to interact with the alert
     * @return the text content of the alert
     */
    public static String getAlertText(WebDriver driver){
        Alert alt = driver.switchTo().alert();
        String result = alt.getText();
        alt.accept();
        return  result;
    }






    /**
     * Navigates to a page by clicking a link with the specified link text.
     *
     * @param Link Text the visible text of the link to click
     * @param driver   the WebDriver instance used to locate and click the link
     */
    public static void navigateToLinkUtil(String linkname, WebDriver driver){
        clickElementUtil(findElementUtil(By.linkText(linkname),driver));

    }




    /**
     * Navigates the browser to the specified URL and maximizes the window.
     *
     * @param url    the URL to navigate to
     * @param driver the WebDriver instance used to perform the navigation
     */
    public static void navigatetoUrlUtil(String url, WebDriver driver) {
        driver.get(url);
        driver.manage().window().maximize();
    }






    /**
     * Finds a web element using the given locator, clicks it, and sends the specified input.
     *
     * @param locator the By locator used to find the element
     * @param driver  the WebDriver instance used to locate and interact with the element
     * @param input   the text input to send to the element
     */
    public static void sendKeysUtil(By locator, WebDriver driver, String input) {
        WebElement element = findElementUtil(locator, driver);
        element.click();
        element.sendKeys(input);
    }




    /**
     * Returns the current URL from the active browser window.
     *
     * @param driver the WebDriver instance
     * @return the current URL as a String
     */
    public static String getCurrentUrlUtil(WebDriver driver) {
        return driver.getCurrentUrl();
    }






    /**
     * Extracts and returns the text content of each cell from a given table row element.
     *
     * @param rowElement the WebElement representing a single table row (<tr>)
     * @return a list of text values from each cell in the row
     */
    public static List<String> getTableRowText(WebElement rowElement) {
        List<WebElement> cells = rowElement.findElements(By.tagName("td"));
        List<String> rowData = new ArrayList<>();
        for (WebElement cell : cells) {
            rowData.add(cell.getText().trim());
        }
        return rowData;
    }





    /**
     * Checks if the given web element is displayed on the page.
     *
     * @param element the WebElement to check
     * @return true if the element is displayed; false if not displayed or if it no longer exists
     */
    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("Element not displayed or no longer exists.");
            return false;
        }
    }





    /**
     * Attempts to click on the provided web element.
     * Logs a message if the element is null or if a stale element exception occurs.
     *
     * @param element the WebElement to click
     */
    public static void clickElementUtil(WebElement element) {
        if (element == null) {
            System.out.println("Cannot click: Element is null");
            return;
        }
        try {
            element.click();
        } catch (StaleElementReferenceException ex) {
            System.out.println("Element Click not happened due to StaleElementReferenceException");
        }
    }






    public static void navigateBackUtil(WebDriver driver) {
//        System.out.println("Navigating back to the previous page.");
        driver.navigate().back();
    }

} // End Of Class
