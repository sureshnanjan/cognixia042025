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
    public static Point getLocationUtil(WebElement element) {
        try {
            Point location = element.getLocation();
            System.out.println("Element location: " + location);
            return location;
        } catch (StaleElementReferenceException ex) {
            System.out.println("Element is stale. Cannot get location.");
            return null;
        } catch (Exception e) {
            System.out.println("Error getting element location: " + e.getMessage());
            return null;
        }
    }


}
