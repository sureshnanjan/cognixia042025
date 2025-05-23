package heroku.tests;

import heroku.selenium.pages.AddToCartPage;
import heroku.selenium.pages.CheckoutPage;
import heroku.selenium.pages.LoginPage;
import heroku.utilities.WebElementInteractions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CheckoutPageTests {

    @Test
    void checkoutShouldBeSuccessful() {
        WebDriver driver = WebElementInteractions.createDriver();

        try {
            // Step 1: Login
            LoginPage loginPage = new LoginPage(driver);
            String username = "admin@microsoft.com";
            String password = "Pass@word1";
            loginPage.login(username, password);

            // Step 2: Add to Cart
            AddToCartPage cartPage = new AddToCartPage(driver);
            cartPage.addItemToCart();

            // Step 3: Checkout
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.completePurchase();

            // Step 4: Assert success
            assertTrue(checkoutPage.isPurchaseSuccessful(),
                    "Purchase was not completed or confirmation message not displayed.");

        } finally {
            driver.quit();
        }
    }

    @Test
    void checkoutShouldRedirectToLoginIfNotLoggedIn() {
        WebDriver driver = WebElementInteractions.createDriver();

        try {
            // NO login performed here

            // Step 1: Add to Cart
            AddToCartPage cartPage = new AddToCartPage(driver);
            cartPage.addItemToCart();

            // Step 2: Click checkout (which should redirect to login)
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.clickCheckoutButton();

            // Step 3: Verify current URL contains /login or similar
            String currentUrl = driver.getCurrentUrl();
            assertTrue(currentUrl.contains("/Identity/Account/Login"),
                    "User was not redirected to login page when attempting checkout without login. Current URL: " + currentUrl);


        } finally {
            //driver.quit();
        }
    }

}
