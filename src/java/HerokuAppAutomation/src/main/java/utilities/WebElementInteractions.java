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

    public static void sendKeysUtil(WebElement element, CharSequence... keysToSend) {
        try {
            if (keysToSend == null) {
                throw new IllegalArgumentException("Keys to send cannot be null");
            }
            element.sendKeys(keysToSend);
            System.out.println("Keys sent to the element successfully");
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid input: " + ex.getMessage());
        } catch (StaleElementReferenceException ex) {
            System.out.println("Element not available to send keys: Stale element");
        } catch (Exception ex) {
            System.out.println("Unexpected error while sending keys: " + ex.getMessage());
        }
    }

}
