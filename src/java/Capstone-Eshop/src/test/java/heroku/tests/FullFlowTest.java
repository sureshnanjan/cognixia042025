package heroku.tests;

import heroku.selenium.pages.RegisterPage;
import heroku.selenium.pages.LoginPage;
import heroku.selenium.pages.AddToCartPage;
import heroku.selenium.pages.CheckoutPage;
import heroku.utilities.WebElementInteractions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FullFlowTest {

    @Test
    void fullUserJourneyRegisterLoginAddToCartCheckout() {
        WebDriver driver = WebElementInteractions.createDriver();

        try {
            // --- Credentials ---
            String email = "admin@microsoft.com";
            String password = "Pass@word1";

            // --- Step 1: Register ---
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.register(email, password, password);

            // --- Step 2: Login ---
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(email, password);

            // --- Step 3: Add to Cart ---
            AddToCartPage addToCartPage = new AddToCartPage(driver);
            addToCartPage.addItemToCart();

            // --- Step 4: Checkout ---
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.completePurchase();

            // --- Step 5: Verify ---
            assertTrue(checkoutPage.isPurchaseSuccessful(),
                    "Purchase was not successful or confirmation message missing.");

        } finally {
            //driver.quit();
        }
    }
}
