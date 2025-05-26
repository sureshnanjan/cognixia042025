/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This utility class performs the complete login operation on the eShop website.
 *  It handles opening the login form, entering credentials, optionally toggling
 *  the remember me checkbox, and submitting the form using Selenium WebDriver.
 * =======================================================================================
 */

package training.eshop.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DoLogin {

    // WebDriver instance for browser control
    private WebDriver driver;

    /**
     * Constructor initializes the DoLogin utility with the WebDriver instance.
     * @param driver WebDriver to use for interactions
     */
    public DoLogin(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Performs the login operation:
     * - Clicks the login button to open login form
     * - Enters email and password
     * - Selects the remember me checkbox if specified
     * - Clicks the submit button to log in
     *
     * @param email User email address to input
     * @param password User password to input
     * @param rememberMe Whether to check the "Remember Me" option
     * @param emailField Locator for email input field
     * @param passwordField Locator for password input field
     * @param rememberMeCheckbox Locator for remember me checkbox
     * @param loginSubmitBtn Locator for the login submit button
     */
    public void doLogin(String email, String password, boolean rememberMe,
                        By emailField, By passwordField, By rememberMeCheckbox, By loginSubmitBtn) {

        // Click login button to open login form
        ClickLogin clickLogin = new ClickLogin(driver);
        clickLogin.doLogin();

        // Enter email address
        WebElement emailElem = WebElementInteractions.findElementUtil(emailField, driver);
        if (emailElem != null) {
            emailElem.clear();
            emailElem.sendKeys(email);
        }

        // Enter password
        WebElement passElem = WebElementInteractions.findElementUtil(passwordField, driver);
        if (passElem != null) {
            passElem.clear();
            passElem.sendKeys(password);
        }

        // Select remember me checkbox if requested
        if (rememberMe) {
            WebElement rememberElem = WebElementInteractions.findElementUtil(rememberMeCheckbox, driver);
            if (rememberElem != null && !rememberElem.isSelected()) {
                rememberElem.click();
            }
        }

        // Click the login submit button
        WebElement loginBtn = WebElementInteractions.findElementUtil(loginSubmitBtn, driver);
        WebElementInteractions.clickElementUtil(loginBtn);
    }
}
