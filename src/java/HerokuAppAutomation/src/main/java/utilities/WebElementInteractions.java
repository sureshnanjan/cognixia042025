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

    public static void navigateToLinkUtil(String linkname, WebDriver driver){
        clickElementUtil(findElementUtil(By.linkText(linkname),driver));

    }

    /**
     * Retrieves the CSS value for a given CSS property from a WebElement.
     * <p>
     * This utility method performs validation on the property name
     * and handles potential exceptions gracefully.
     * </p>
     *
     * @param element      The WebElement from which to retrieve the CSS value.
     * @param propertyName The name of the CSS property (e.g., "font-size", "color").
     * @return The CSS value as a String, or {@code null} if an error occurs.
     */
    public static String getCssValueUtil(WebElement element, String propertyName) {
        try {
            // Check if the property name is null or empty
            if (propertyName == null || propertyName.isEmpty()) {
                throw new IllegalArgumentException("CSS property name cannot be null or empty");
            }

            // Retrieve the CSS value from the element
            String value = element.getCssValue(propertyName);
            System.out.println("CSS Value retrieved successfully: " + value);
            return value;

        } catch (IllegalArgumentException ex) {
            // Handles invalid or missing property name input
            System.out.println("Invalid input: " + ex.getMessage());

        } catch (Exception ex) {
            // Catches any unexpected errors such as stale element or driver issues
            System.out.println("Unexpected error while getting CSS value: " + ex.getMessage());
        }

        // Return null if retrieval fails
        return null;
    }

} // End Of Class
