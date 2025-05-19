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

    public static void clearTextUtil(WebElement element) {
        try {
            // Try to clear the content of the input or text field
            element.clear();
        } catch (Exception ex) {
            // If any error occurs (e.g., element not interactable), print a warning
            System.out.println("Unable to clear text from the element.");
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



} // End Of Class
