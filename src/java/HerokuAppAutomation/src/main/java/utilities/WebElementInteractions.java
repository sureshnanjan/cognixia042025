/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */


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

    /**
     * Utility method to check whether a particular web element is displayed on the page.
     * This method attempts to call {@code isDisplayed()} on the given {@link WebElement}.
     * If the element is present and visible, it returns {@code true}. If the element is not found
     * in the DOM, a {@link NoSuchElementException} is caught and {@code false} is returned.
     * </p>
     *
     * @param element The {@link WebElement} to be checked for visibility.
     * @return {@code true} if the element is displayed, {@code false} if it is not present or not displayed.
     */
    public static boolean isDisplayedUtil(WebElement element) {
        try {
            // Attempt to check if the element is displayed
            return element.isDisplayed();
        } catch (NoSuchElementException ex) {
            // Log message when the element is not found in the DOM
            System.out.println("Element is not displayed: " + ex.getMessage());
            return false;
        }
    }


}
