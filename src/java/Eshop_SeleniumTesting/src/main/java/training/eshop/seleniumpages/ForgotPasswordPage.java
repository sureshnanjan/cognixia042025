// Copyright (c) 2025 EShop Automation Framework. All rights reserved.
// Developed by Subalakshmi and Team for internal automation testing purposes.

package training.eshop.seleniumpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import training.eshop.operations.ForgotPasswordOperations;
import training.eshop.utilities.WebElementInteractions;

import static training.eshop.utilities.WebElementInteractions.*;

public class ForgotPasswordPage implements ForgotPasswordOperations {

    private WebDriver browser;

    // Page Locators for Forgot Password functionality
    private By titleIdentifier;
    private By emailField;
    private By sendButton;

    // Constructor initializes browser, navigates to login page, and clicks "Forgot password"
    public ForgotPasswordPage() {
        this.browser = new ChromeDriver();
        WebElementInteractions.navigatetoUrlUtil("http://34.46.74.231:5106/Identity/Account/Login", this.browser);

        // Step 1: Click the "Forgot password" link
        By forgotPasswordLink = By.xpath("/html/body/div/div/div/div/section/form/div[6]/p[1]/a");
        WebElement linkElement = findElementUtil(forgotPasswordLink, browser);
        clickElementUtil(linkElement);

        // Step 2: Now on the Forgot Password page
        titleIdentifier = By.xpath("/html/body/div/h2");
        emailField = By.id("Input_Email");
        sendButton = By.xpath("/html/body/div/div/div/form/button");
    }

    // Returns the current page title
    @Override
    public String getTitle() {
        return getCurrentPageTitle(browser);
    }

    // Performs email input and clicks the Send button
    @Override
    public void ChangePassword(String email) {
        WebElement emailInput = findElementUtil(emailField, browser);
        enterTextUtil(emailInput, email);
        clickElementUtil(findElementUtil(sendButton, browser));
    }
}
