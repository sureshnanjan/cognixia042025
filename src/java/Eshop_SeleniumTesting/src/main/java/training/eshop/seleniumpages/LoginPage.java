/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class represents the Login Page of the eShop application.
 *  It provides functionality to perform login and logout operations,
 *  retrieve page title, check login button visibility,
 *  and fetch the currently logged-in username.
 *  The class uses Selenium WebDriver for browser automation
 *  and WebElementInteractions utility methods for element handling and waits.
 * =======================================================================================
 */

package training.eshop.seleniumpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import training.eshop.operations.LoginPageOperations;
import training.eshop.utilities.ClickLogin;
import training.eshop.utilities.DoLogin;
import training.eshop.utilities.WebElementInteractions;

import static training.eshop.utilities.WebElementInteractions.*;

public class LoginPage implements LoginPageOperations {
    private WebDriver _driver;

    // Locators for login page elements
    private final By emailField;
    private final By passwordField;
    private final By rememberMeCheckbox;
    private final By loginSubmitBtn;
    private final By usernameLocator;
    private By logoutBtnLocator;
    private By loginBtnLocator;

    /**
     * Constructor initializes ChromeDriver and navigates to the login page URL.
     * Also initializes all necessary locators for the page elements.
     */
    public LoginPage() {
        this._driver = new ChromeDriver();
        WebElementInteractions.navigatetoUrlUtil("http://35.193.6.1:5106", this._driver);

        emailField = By.xpath("//*[@id='Input_Email']");
        passwordField = By.xpath("//*[@id='Input_Password']");
        rememberMeCheckbox = By.xpath("//*[@id='Input_RememberMe']");
        loginSubmitBtn = By.xpath("/html/body/div/div/div/div/section/form/div[5]/button");
        usernameLocator = By.xpath("//*[@id='logoutForm']/section[1]/div");
        logoutBtnLocator = By.xpath("//*[@id='logoutForm']/section[2]/a[3]/div");
        loginBtnLocator = By.xpath("/html/body/div/header/div/article/section[2]/div/section/div/a");
    }

    /**
     * Performs a click on the login button and returns the page title.
     * @return current page title as a String.
     */
    public String getTitle() {
        ClickLogin button = new ClickLogin(_driver);
        button.doLogin();
        return getCurrentPageTitle(_driver);
    }

    /**
     * Executes the login operation with given credentials and rememberMe flag.
     * @param username email or username
     * @param password user password
     * @param rememberMe whether to check the 'Remember Me' box
     */
    @Override
    public void doLogin(String username, String password, boolean rememberMe) {
        DoLogin loginUtil = new DoLogin(_driver);
        loginUtil.doLogin(username, password, rememberMe,
                emailField, passwordField, rememberMeCheckbox, loginSubmitBtn);
    }

    /**
     * Performs logout operation after logging in.
     * It first logs in, then clicks on the username to open logout options,
     * waits for 1 second using WebElementInteractions.sleep, and then clicks logout button.
     * @param username email or username
     * @param password user password
     * @param rememberMe whether to check 'Remember Me'
     */
    @Override
    public void doLogout(String username, String password, boolean rememberMe) {
        DoLogin loginUtil = new DoLogin(_driver);
        loginUtil.doLogin(username, password, rememberMe,
                emailField, passwordField, rememberMeCheckbox, loginSubmitBtn);

        WebElement userElement = WebElementInteractions.findElementUtil(usernameLocator, _driver);
        if (userElement != null) {
            userElement.click();

            // wait briefly to let logout button appear
            WebElementInteractions.sleep(1000);  // Use WebElementInteractions.sleep instead of Thread.sleep
        }

        WebElement logoutBtn = WebElementInteractions.findElementUtil(logoutBtnLocator, _driver);
        WebElementInteractions.clickElementUtil(logoutBtn);
    }

    /**
     * Retrieves the logged in username displayed on the page.
     * @return username text or null if not found.
     */
    public String getLoggedInUsername() {
        WebElement userElement = WebElementInteractions.findElementUtil(usernameLocator, _driver);
        return userElement != null ? userElement.getText().trim() : null;
    }

    /**
     * Checks if the login button is visible on the page after logout.
     * @return true if login button is present, false otherwise.
     */
    public boolean isLoginButtonVisible() {
        return WebElementInteractions.findElementUtil(loginBtnLocator, _driver) != null;
    }
}
