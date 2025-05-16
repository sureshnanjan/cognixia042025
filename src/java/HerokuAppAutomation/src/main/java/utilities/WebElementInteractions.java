package utilities;

import org.openqa.selenium.*;

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

    public static boolean isVisible(WebElement element)
    {
        try {
            return element.isDisplayed();
        }
        catch (StaleElementReferenceException ex) {
            System.out.println("Cannot check if element is visible due to StaleElementReferenceException");
            return false;
        }
        catch (Exception e) {
            System.out.println("Exception occurred while checking visibility: " + e.getMessage());
            return false;
        }
    }
}

