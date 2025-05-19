/*
 * Copyright (c) 2025 Sobiya Kumar
 *
 * This software is the confidential and proprietary information of
 * Sobiya Kumar ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement.
 *
 * Unauthorized copying, modification, or distribution of this file,
 * via any medium is strictly prohibited.
 */


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

    public static void navigateToLinkUtil(String linkname, WebDriver driver){
        clickElementUtil(findElementUtil(By.linkText(linkname),driver));

    }

    /**
     * Finds all elements matching the given locator on the current page.
     *
     * @param locator The By locator used to find elements (e.g., By.id, By.cssSelector)
     * @param driver  The WebDriver instance to interact with the browser
     * @return A list of WebElements matching the locator; returns an empty list if no elements found or on exception
     */
    public static List<WebElement> findElements(By locator, WebDriver driver){
        try {
            // Find all elements matching the locator
            List<WebElement> elements = driver.findElements(locator);

            // Check if no elements were found and print a message
            if(elements.isEmpty()) {
                System.out.println("No elements found for locator: " + locator);
            }

            // Return the list of found elements (could be empty)
            return elements;
        } catch(Exception ex){
            // If any exception occurs during finding elements, print error message
            System.out.println("Exception occurred while finding elements: " + ex.getMessage());

            // Return an empty list to avoid returning null and causing NullPointerExceptions
            return Collections.emptyList();
        }
    }

} // End Of Class
