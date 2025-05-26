/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  JUnit 5 test class for Basket Page functionality in the eShop project.
 *  Tests include adding items, updating quantities, price verification, and proceeding to checkout.
 * =======================================================================================
 */

package training.eshop.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import training.eshop.seleniumpages.BasketPage;

import static org.junit.jupiter.api.Assertions.*;

public class BasketPageTests {

    private BasketPage page;

    /**
     * Test that adding an item to the basket does not throw any exceptions.
     */
    @Test
    void AddItemToBasketReturnsExpectedText() {
        page = new BasketPage();
        String result = page.addItemToBasket("PRISM WHITE T-SHIRT");
        assertEquals("Item added to cart: PRISM WHITE T-SHIRT", result);
    }


    /**
     * Test that updating the item count works without throwing exceptions.
     */
    @Test
    void UpdateItemCountWorks() {
        page = new BasketPage();
        String updatedCount = page.checkUpdateItemCount();
        assertEquals("2", updatedCount, "Item count did not update as expected.");
    }

    /**
     * Test that price updates correctly after changing quantity.
     */
    @Test
    void PriceUpdateCheckReturnsCorrectPrice() {
        page = new BasketPage();
        String price = page.checkPriceUpdate();
        assertEquals("$ 36.00", price, "Price did not update as expected.");
    }



    /**
     * Test that proceeding to the checkout page works without throwing exceptions.
     */

@Test
void ProceedToCheckoutReturnsTrueWhenPayNowButtonPresent() {
    page = new BasketPage();
    boolean isCheckoutSuccessful = page.proceedToCheckout();
    assertTrue(isCheckoutSuccessful, "Checkout did not complete successfully; Pay Now button not found.");
}


    /**
     * Tear down method to close the browser after each test.
     * Assumes BasketPage has a getBrowser() method exposing the WebDriver instance.
     */
    @AfterEach
    void tearDown() {
        if (page != null) {
            page.getBrowser().quit(); // Ensure getBrowser() exists in BasketPage to expose the driver
        }
    }
}
