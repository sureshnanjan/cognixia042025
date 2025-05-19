package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class WebElementInteractions {
    public static void navigatetoUrlUtil(String url){}
    public static WebElement findElementUtil(By locator, WebDriver driver){
        try{
            return driver.findElement(locator);

        }catch(NoSuchElementException ex){
            System.out.println("Intercation Not Possible");
            return  null;
        }
    }

    public static String getTextUtil(WebElement element){
        System.out.println("Getting Text from the element");
        return element.getText();
    }

    public static void clickElementUtil(WebElement element){
        try {
            element.click();
        }catch (StaleElementReferenceException ex){
            System.out.println("Element Click not happened");
        }
    }

    public static void AuthenticateUtil(String uname, String password, AuthMethods auth, String url, WebDriver driver){
        switch (auth){
            case Basic:
                driver.get(String.format("https://%s:%s@%s",uname,password,url));
                break;
            case Digest:
                break;
        }

    }

    public static void RightClickUtil(WebElement element, WebDriver driver){
        Actions actions = new Actions(driver);
        actions.contextClick(element);
        actions.perform();

    }

    public static String getAlertText(WebDriver driver){
        Alert alt = driver.switchTo().alert();
        String result = alt.getText();
        alt.accept();
        return  result;
    }

    /**
     * Sends the specified keys to the given WebElement.
     * <p>
     * This utility method ensures that:
     * <ul>
     *   <li>The input keys are not null</li>
     *   <li>Exceptions like StaleElementReferenceException are handled gracefully</li>
     *   <li>Unexpected runtime exceptions are caught and logged</li>
     * </ul>
     *
     * @param element     the WebElement to which keys should be sent
     * @param keysToSend  one or more characters to be sent to the element
     */
    public static void sendKeysUtil(WebElement element, CharSequence... keysToSend) {
        try {
            // Validate that the input keys are not null
            if (keysToSend == null) {
                throw new IllegalArgumentException("Keys to send cannot be null");
            }

            // Send keys to the provided element
            element.sendKeys(keysToSend);
            System.out.println("Keys sent to the element successfully");

        } catch (IllegalArgumentException ex) {
            // Handles the case when null or invalid input is provided
            System.out.println("Invalid input: " + ex.getMessage());

        } catch (StaleElementReferenceException ex) {
            // Handles the case when the DOM element is no longer attached
            System.out.println("Element not available to send keys: Stale element");

        } catch (Exception ex) {
            // Catches any other unexpected exceptions
            System.out.println("Unexpected error while sending keys: " + ex.getMessage());
        }
    }

    public static void navigateToLinkUtil(String linkname, WebDriver driver){
        clickElementUtil(findElementUtil(By.linkText(linkname),driver));

    }
}
