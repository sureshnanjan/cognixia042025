package heroku.tests;

import heroku.operations.AddToCartPageOperations;
import heroku.selenium.pages.AddToCartPage;
import heroku.utilities.WebElementInteractions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddToCartPageTests {

    @Test
    void addToCartShouldBeSuccessful() {
        WebDriver driver = WebElementInteractions.createDriver();

        try {
            AddToCartPageOperations addToCartPage = new AddToCartPage(driver);
            addToCartPage.addItemToCart();

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains("/Basket"));

            String expectedPartialUrl = "/Basket";
            assertTrue(driver.getCurrentUrl().contains(expectedPartialUrl),
                    "Item was not added to cart or user was not redirected properly");
        } finally {
            driver.quit();
        }
    }

    @Test
    void addToCartShouldFailWithValidFilterButNoResults() {
        WebDriver driver = WebElementInteractions.createDriver();

        try {
            AddToCartPage page = new AddToCartPage(driver);

            // Select options that return no products
            page.selectBrandNoResults();
            page.selectTypeNoResults();
            page.clickFilterApplyButton();

            Thread.sleep(2000); // Wait for product list to refresh

            boolean isAddToBasketPresent = WebElementInteractions.isElementPresent(
                    By.xpath("/html/body/div/div/div[2]/div[1]/form/input[1]"), driver);

            assertFalse(isAddToBasketPresent, "Expected no Add to Basket button, but it appeared.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

}
