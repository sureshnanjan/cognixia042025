/**
 * ----------------------------------------------------------------------------
 * Copyright © 2025 Capstone Project Team. All rights reserved.
 *
 * This class is part of the Selenium automation suite for the eShop website.
 *
 * Team Members:
 * - Athesh Alagarsamy
 * - Pavithra Rajendiran
 * - Subalakshmi T
 * - Vaishnavi Sivakumar
 * ----------------------------------------------------------------------------
 */

package training.eshop.seleniumpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import training.eshop.operations.BasketPageOperations;
import training.eshop.utilities.ClickLogin;
import training.eshop.utilities.DoLogin;
import training.eshop.utilities.WebElementInteractions;

import static training.eshop.utilities.WebElementInteractions.*;

/**
 * This class contains the implementation for basket/cart operations
 * like adding items, updating quantity, checking price updates,
 * and proceeding to checkout on the eShop website.
 */
public class BasketPage implements BasketPageOperations {

    private WebDriver browser;

    // Element Locators
    private By itemLocator;
    private By addItemButton;
    private By itemCountField;
    private By updateButton;
    private By itemPriceField;
    private By checkoutButton;
    private By basketLocator;
    private By emailField;
    private By passwordField;
    private By rememberMeCheckbox;
    private By loginSubmitBtn;
    private By productImage;
    private By payNowButton;
    private By itemNameLocator;

    /**
     * Constructor - initializes browser, navigates to the site,
     * performs login, and initializes element locators.
     */
    public BasketPage() {
        this.browser = new ChromeDriver();
        WebElementInteractions.navigatetoUrlUtil("http://35.193.6.1:5106", this.browser);
        browser.manage().window().maximize();

        // Initialize login locators
        emailField = By.id("Input_Email");
        passwordField = By.id("Input_Password");
        rememberMeCheckbox = By.id("Input_RememberMe");
        loginSubmitBtn = By.xpath("/html/body/div/div/div/div/section/form/div[5]/button");

        // Click login and perform login
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

        // Initialize element locators for basket interactions
        itemLocator = By.id("/html/body/div/div/div[2]/div[1]/form/input[1]");
        addItemButton = By.xpath("/html/body/div/div/div[2]/div[1]/form/input[1]");
        itemCountField = By.xpath("/html/body/div/div/form/div/article/div[1]/section[4]/input[2]");
        updateButton = By.xpath("/html/body/div/div/form/div/div[3]/section[2]/button");
        itemPriceField = By.xpath("/html/body/div/div/form/div/article/div[1]/section[5]");
        checkoutButton = By.xpath("/html/body/div/div/form/div/div[3]/section[2]/a");
        basketLocator = By.xpath("/html/body/div/header/div/article/section[3]/a/div[1]");
        productImage = By.xpath("/html/body/div/div/form/div/article/div[1]/section[1]/img");
        payNowButton = By.xpath("/html/body/div/div/form/div/div[3]/section[2]/input");
        itemNameLocator = By.xpath("/html/body/div/div/form/div/article/div[1]/section[2]");
    }

    /**
     * Returns the title of the current page.
     */
    public String getTitle() {
        return getCurrentPageTitle(browser);
    }

    /**
     * Adds an item to the basket using the provided item name.
     * Validates if the item is displayed in the cart afterward.
     */
    @Override
    public String addItemToBasket(String itemName) {
        scrollBy(0, 500);
        findElementUtil(itemLocator, browser);  // Navigate to product section (if needed)
        clickElementUtil(findElementUtil(addItemButton, browser));
        sleep(3000);

        // Validate the added item by checking its name
        WebElement addedItem = findElementUtil(itemNameLocator, browser);

        if (addedItem == null || !addedItem.isDisplayed()) {
            return "Item not added: " + itemName;
        } else {
            return "Item added to cart: " + itemName;
        }
    }

    /**
     * Updates the item count in the cart to 2 and returns the updated value.
     */
    @Override
    public String checkUpdateItemCount() {
        sleep(5000);
        clickElementUtil(findElementUtil(basketLocator, browser));
        scrollBy(0, 1000);
        sleep(2000);

        WebElement countField = findElementUtil(itemCountField, browser);
        enterTextUtil(countField, "2");
        clickElementUtil(findElementUtil(updateButton, browser));

        sleep(2000);
        WebElement updatedCountField = findElementUtil(itemCountField, browser);
        return updatedCountField.getAttribute("value");
    }

    /**
     * Updates the item count to 3 and returns the updated price.
     */
    @Override
    public String checkPriceUpdate() {
        sleep(5000);
        clickElementUtil(findElementUtil(basketLocator, browser));
        scrollBy(0, 1000);
        sleep(2000);

        WebElement countField = findElementUtil(itemCountField, browser);
        enterTextUtil(countField, "3");
        clickElementUtil(findElementUtil(updateButton, browser));

        sleep(2000);
        WebElement priceElement = findElementUtil(itemPriceField, browser);
        scrollBy(0, 1000);
        sleep(2000);

        return priceElement.getText();
    }

    /**
     * Proceeds to the checkout page and returns whether the Pay Now button is present.
     */
    @Override
    public boolean proceedToCheckout() {
        sleep(5000);
        clickElementUtil(findElementUtil(basketLocator, browser));
        scrollBy(0, 1000);
        sleep(2000);

        clickElementUtil(findElementUtil(checkoutButton, browser));
        sleep(2000);
        scrollBy(0, 1000);

        return isElementPresent(payNowButton, browser);
    }

    /**
     * Utility to pause thread execution for a given time.
     */
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted while sleeping.");
        }
    }

    /**
     * Utility to scroll the browser window by a given x and y offset.
     */
    private void scrollBy(int x, int y) {
        ((JavascriptExecutor) browser).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    /**
     * Returns the current instance of WebDriver.
     */
    public WebDriver getBrowser() {
        return browser;
    }
}
