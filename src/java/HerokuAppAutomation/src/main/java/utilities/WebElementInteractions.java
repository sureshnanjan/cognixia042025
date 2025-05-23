package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class WebElementInteractions {
    public static void navigatetoUrlUtil(String url, WebDriver driver){
        System.out.println("Starting Navigating");
        driver.get(url);

    }
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
    public static String getAttributeUtil(WebElement element, String attributeName) {
        System.out.println("Getting attribute '" + attributeName + "' from the element");
        try {
            return element.getAttribute(attributeName);
        } catch (Exception ex) {
            System.out.println("Failed to get attribute: " + ex.getMessage());
            return null;
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

} // End Of Class
