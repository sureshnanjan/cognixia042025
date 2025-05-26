/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This utility class handles the action of clicking the login button on the eShop website.
 *  It encapsulates locating and clicking the login button element using Selenium WebDriver.
 * =======================================================================================
 */

package training.eshop.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickLogin {

    // WebDriver instance for browser control
    private WebDriver driver;

    // Locator for the login button on the page
    private By loginButton = By.xpath("/html/body/div/header/div/article/section[2]/div/section/div/a");

    /**
     * Constructor initializes the ClickLogin utility with the WebDriver instance.
     * @param driver WebDriver to use for interactions
     */
    public ClickLogin(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Performs the click action on the login button element.
     * Uses WebElementInteractions utility methods to find and click the element safely.
     */
    public void doLogin() {
        WebElement element = WebElementInteractions.findElementUtil(loginButton, driver);
        WebElementInteractions.clickElementUtil(element);
    }
}
