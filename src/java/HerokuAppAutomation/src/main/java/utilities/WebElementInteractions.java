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
    public static String getAttributeUtil(WebElement element, String attributeName) {
        System.out.println("Getting attribute '" + attributeName + "' from the element");
        try {
            return element.getAttribute(attributeName);
        } catch (Exception ex) {
            System.out.println("Failed to get attribute: " + ex.getMessage());
            return null;
        }
    }

}
