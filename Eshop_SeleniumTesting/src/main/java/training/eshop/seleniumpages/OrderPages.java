/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class represents the Order Pages of the eShop application.
 *  It handles operations related to orders such as fetching the page title,
 *  counting the orders, checking order details, and managing login/logout flow.
 *  It uses Selenium WebDriver for browser automation and WebElementInteractions
 *  utility methods for interacting with web elements.
 * =======================================================================================
 */

package training.eshop.seleniumpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import training.eshop.operations.OrderOperations;
import training.eshop.utilities.*;

import java.util.List;

import static training.eshop.utilities.WebElementInteractions.*;

public class OrderPages implements OrderOperations {

    private WebDriver browser;

    // Locators for order page elements
    private By titleIdentifier;
    private By ordersArticles;
    private By orderDetailsButton;

    // Locators for login elements
    private By emailField;
    private By passwordField;
    private By rememberMeCheckbox;
    private By loginSubmitBtn;

    /**
     * Constructor initializes ChromeDriver, navigates to the eShop URL,
     * performs login with credentials, navigates to the Orders page,
     * and initializes the locators related to orders.
     */
    public OrderPages() {
        this.browser = new ChromeDriver();
        navigatetoUrlUtil("http://35.193.6.1:5106/", this.browser);

        // Initialize login locators
        emailField = By.id("Input_Email");
        passwordField = By.id("Input_Password");
        rememberMeCheckbox = By.id("Input_RememberMe");
        loginSubmitBtn = By.xpath("/html/body/div/div/div/div/section/form/div[5]/button");

        // Click initial login and then perform login with given credentials
        ClickLogin clickLogin = new ClickLogin(browser);
        clickLogin.doLogin();

        DoLogin loginUtil = new DoLogin(browser);
        loginUtil.doLogin(
                "athesh@gmail.com",
                "Athesh@2026",
                false,
                emailField,
                passwordField,
                rememberMeCheckbox,
                loginSubmitBtn
        );

        // Click on the username dropdown to open logout/options menu
        WebElement usernameDropdown = browser.findElement(By.id("logoutForm"));
        usernameDropdown.click();

        // Wait briefly for options to appear (replaced Thread.sleep with utility sleep)
        WebElementInteractions.sleep(1000);

        // Click on the 'My Orders' link from the options
        WebElement myOrderLink = browser.findElement(By.xpath("//*[@id=\"logoutForm\"]/section[2]/a[1]"));
        myOrderLink.click();

        // Initialize order page locators
        titleIdentifier = By.xpath("/html/body/div/div/div/h1");
        ordersArticles = By.xpath("/html/body/div/div/div/article[1]");
        orderDetailsButton = By.xpath("/html/body/div/div/div/article[2]/section[5]/a");
    }

    /**
     * Returns the title text of the Orders page.
     * @return page title string
     */
    @Override
    public String getTitle() {
        WebElement titleElement = findElementUtil(titleIdentifier, browser);
        return getTextUtil(titleElement);
    }

    /**
     * Returns the number of orders found on the Orders page.
     * @return count of order articles
     */
    @Override
    public int getOrderCount() {
        List<WebElement> orders = browser.findElements(ordersArticles);
        return orders.size();
    }

    /**
     * Clicks the order details button to view details of an order.
     * Throws RuntimeException if the button is not found.
     */
    @Override
    public void checkOrderDetails() {
        WebElement detailsBtn = findElementUtil(orderDetailsButton, browser);
        if (detailsBtn == null) {
            throw new RuntimeException("Order details button not found.");
        }
        clickElementUtil(detailsBtn);
    }

    /**
     * Closes the browser and quits the WebDriver session.
     */
    public void closeBrowser() {
        if (browser != null) {
            browser.quit();
        }
    }
}
