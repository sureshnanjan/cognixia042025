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
    public static Dimension getSizeUtil(WebElement element) {
        if (element == null) {
            System.out.println("Element is null");
            return new Dimension(0, 0);
        }
        System.out.println("Getting size of the element");
        return element.getSize();
    }



}
