/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class represents the Register Page of the eShop application.
 *  It provides methods to get the page title and perform user registration
 *  by filling the registration form and submitting it.
 *  Uses Selenium WebDriver and utility methods from WebElementInteractions for interaction.
 * =======================================================================================
 */

package training.eshop.seleniumpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import training.eshop.operations.RegisterPageOperations;
import training.eshop.utilities.WebElementInteractions;

import static training.eshop.utilities.WebElementInteractions.*;

public class RegisterPage implements RegisterPageOperations {

    private WebDriver browser;

    // Locators for registration page elements
    private By titleIdentifier;
    private By usernameField;
    private By passwordField;
    private By confirmPasswordField;
    private By registerButton;

    /**
     * Constructor initializes ChromeDriver and navigates to the Register page URL.
     * Also initializes the locators based on the actual page structure.
     */
    public RegisterPage() {
        this.browser = new ChromeDriver();
        navigatetoUrlUtil("http://35.193.6.1:5106/Identity/Account/Register", this.browser);

        // Initialize locators for registration form fields and button
        usernameField = By.id("Input_Email");
        passwordField = By.id("Input_Password");
        confirmPasswordField = By.id("Input_ConfirmPassword");
        registerButton = By.xpath("/html/body/div/div/div/div/form/button");
    }

    /**
     * Returns the title of the current page.
     * @return page title string
     */
    @Override
    public String getTitle() {
        return getCurrentPageTitle(browser);
    }

    /**
     * Performs user registration by entering the username, password,
     * confirm password, and clicking the register button.
     * @param username User email/username
     * @param password User password
     * @param confirmPassword Confirmation of the password
     */
    @Override
    public void doRegister(String username, String password, String confirmPassword) {
        enterTextUtil(findElementUtil(usernameField, browser), username);
        enterTextUtil(findElementUtil(passwordField, browser), password);
        enterTextUtil(findElementUtil(confirmPasswordField, browser), confirmPassword);
        clickElementUtil(findElementUtil(registerButton, browser));
    }
}
