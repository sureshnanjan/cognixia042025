/*
 * Copyright (c) 2025 Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 * All rights reserved.
 *
 * This class implements the AccountDetailsOperations interface to automate
 * interactions on the Account Details page of the eShop application using Selenium WebDriver.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package training.eshop.seleniumpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import training.eshop.operations.AccountDetailsOperations;
import training.eshop.operations.EshopOperations;
import training.eshop.utilities.AccountDetails;
import training.eshop.utilities.*;
import training.eshop.utilities.WebElementInteractions;

import static training.eshop.utilities.WebElementInteractions.*;

public class AccountDetailsPage implements AccountDetailsOperations {

    // WebDriver instance for browser automation
    private WebDriver browser;

    // Locators for elements on the Account Details page
    private By titleIdentifier;
    private By phoneNumberField;
    private By saveProfileButton;
    private By currentPasswordField;
    private By newPasswordField;
    private By confirmPasswordField;
    private By changePasswordButton;
    private By confirmationMessageLocator;
    private By clickPasswordBtn;
    private By myAccountLocator;
    private By usernameLocator;

    // Constructor to initialize WebDriver, perform login, and navigate to Account Details page
    public AccountDetailsPage() {
        this.browser = new ChromeDriver();
        WebElementInteractions.navigatetoUrlUtil("http://35.193.6.1:5106", this.browser);

        // Login credentials and field locators
        By emailField = By.xpath("//*[@id='Input_Email']");
        By passwordField = By.xpath("//*[@id='Input_Password']");
        By rememberMeCheckbox = By.xpath("//*[@id='Input_RememberMe']");
        By loginSubmitBtn = By.xpath("/html/body/div/div/div/div/section/form/div[5]/button");

        // Perform login using DoLogin utility
        DoLogin doLoginUtil = new DoLogin(browser);
        doLoginUtil.doLogin("suba@gmail.com", "Suba@2025", false,
                emailField, passwordField, rememberMeCheckbox, loginSubmitBtn);

        WebElementInteractions.sleep(3000);
        // Wait and navigate to My Account section
        usernameLocator = By.xpath("//*[@id='logoutForm']/section[1]/div");
        WebElement usernameElement = WebElementInteractions.findElementUtil(usernameLocator, browser);
        if (usernameElement != null) {
            usernameElement.click();

            try {
                Thread.sleep(1000); // Wait for dropdown to appear
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        WebElementInteractions.sleep(3000);
        confirmationMessageLocator = By.xpath("/html/body/div/div/div/div[2]/div[1]");
        myAccountLocator = By.xpath("//*[@id='logoutForm']/section[2]/a[2]");
        WebElement myAccountElement = WebElementInteractions.findElementUtil(myAccountLocator, browser);
        WebElementInteractions.clickElementUtil(myAccountElement);

        // Initialize field locators
        titleIdentifier = By.xpath("/html/body/div/div/h2");
        phoneNumberField = By.id("PhoneNumber");
        saveProfileButton = By.xpath("/html/body/div/div/div/div[2]/div/div/form/button");
        currentPasswordField = By.id("OldPassword");
        newPasswordField = By.id("NewPassword");
        confirmPasswordField = By.id("ConfirmPassword");
        changePasswordButton = By.xpath("/html/body/div/div/div/div[2]/div/div/form/button");
        clickPasswordBtn = By.xpath("/html/body/div/div/div/div[1]/ul/li[2]/a");
    }

    // Returns the current page title
    @Override
    public String getTitle() {

        return getCurrentPageTitle(browser);
    }

    // Throws exception since email verification is not supported
    @Override
    public void SendVerificationEmail() {
        throw new UnsupportedOperationException("Send Verification Email is not supported.");
    }

    // Changes the phone number in the profile and saves it
    @Override
    public void ChangeProfile(String phoneNumber) {
        WebElement phoneField = findElementUtil(phoneNumberField, browser);
        enterTextUtil(phoneField, phoneNumber);
        clickElementUtil(findElementUtil(saveProfileButton,browser));
    }

    // Changes the user's password using the form fields
    @Override
    public void ChangePassword(String currentPassword, String newPassword, String confirmPassword) {
        enterTextUtil(findElementUtil(currentPasswordField, browser), currentPassword);
        enterTextUtil(findElementUtil(newPasswordField, browser), newPassword);
        enterTextUtil(findElementUtil(confirmPasswordField, browser), confirmPassword);
        clickElementUtil(findElementUtil(changePasswordButton,browser));
    }

    // Changes the password and returns the confirmation message
    public String changePasswordAndReturnMessage(String currentPassword, String newPassword, String confirmPassword) {
        clickElementUtil(findElementUtil(clickPasswordBtn, browser));
        WebElementInteractions.sleep(2000);
        enterTextUtil(findElementUtil(currentPasswordField, browser), currentPassword);
        enterTextUtil(findElementUtil(newPasswordField, browser), newPassword);
        enterTextUtil(findElementUtil(confirmPasswordField, browser), confirmPassword);
        clickElementUtil(findElementUtil(changePasswordButton, browser));

        // Get confirmation message after password change
        WebElementInteractions.sleep(2000);
        WebElement messageElement = findElementUtil(confirmationMessageLocator, browser);
        return messageElement != null ? messageElement.getText().trim() : null;
    }


    // Throws exception since 2FA is not supported
    @Override
    public void TwoFactorAuthentication() {
        throw new UnsupportedOperationException("Two-Factor Authentication is not supported.");
    }

    // Retrieves the phone number currently displayed in the form
    @Override
    public String getPhoneNumber() {
        WebElement phoneField = findElementUtil(phoneNumberField, browser);
        return phoneField != null ? phoneField.getAttribute("value") : null;
    }

    // Closes the browser instance to free up resources
    public void closeBrowser() {
        if (browser != null) {
            browser.quit();
        }
    }

}
