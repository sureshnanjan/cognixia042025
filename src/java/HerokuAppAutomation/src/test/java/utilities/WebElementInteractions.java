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


    // getSize() Retrieves the size (width and height) of a web element located by the given locator.
    public static Dimension getSize(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator); // Locate the web element
            return element.getSize(); // Return its size (Dimension: width and height)
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + locator.toString());
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving element size: " + e.getMessage());
        }
        return null; // Return null if any exception occurs
    }

} // End Of Class
