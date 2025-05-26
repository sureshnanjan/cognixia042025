package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.util.ArrayList;
import java.util.List;

public class WebElementInteractions {


    public static WebElement findElementUtil(By locator, WebDriver driver){
        try{
            return driver.findElement(locator);

        }catch(NoSuchElementException ex){
            System.out.println("Interaction Not Possible");
            return  null;
        }
    }

    public static String getTextUtil(WebElement element){
        System.out.println("Getting Text from the element");
        return element.getText();
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


    public static void navigatetoUrlUtil(String url, WebDriver driver) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static void sendKeysUtil(By locator, WebDriver driver, String input) {
        WebElement element = findElementUtil(locator, driver);
        element.click();
        element.sendKeys(input);
    }

    public static String getCurrentUrlUtil(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public static List<String> getTableRowText(WebElement rowElement) {
        List<WebElement> cells = rowElement.findElements(By.tagName("td"));
        List<String> rowData = new ArrayList<>();
        for (WebElement cell : cells) {
            rowData.add(cell.getText().trim());
        }
        return rowData;
    }

    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("Element not displayed or no longer exists.");
            return false;
        }
    }

    public static void clickElementUtil(WebElement element) {
        if (element == null) {
            System.out.println("Cannot click: Element is null");
            return;
        }
        try {
            element.click();
        } catch (StaleElementReferenceException ex) {
            System.out.println("Element Click not happened due to StaleElementReferenceException");
        }
    }


    public static void navigateBackUtil(WebDriver driver) {
//        System.out.println("Navigating back to the previous page.");
        driver.navigate().back();
    }

} // End Of Class
