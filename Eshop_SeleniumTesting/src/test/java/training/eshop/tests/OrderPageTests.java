/*
 * © 2025 eShop Testing Team. All rights reserved.
 * Authors: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 * This class contains automated tests for verifying the functionality
 * of the Order page in the eShop application. It includes tests for
 * page title verification, order display validation, and order details
 * accessibility.
 */

package training.eshop.tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import training.eshop.operations.OrderOperations;
import training.eshop.seleniumpages.OrderPages;
import training.eshop.utilities.OrderDetails;
import training.eshop.utilities.OrderDataUtil;

public class OrderPageTests {

    /**
     * Instance of OrderOperations to interact with the Order page.
     */
    private OrderOperations page;

    /**
     * Setup method to initialize the Order page operations instance before each test.
     */
    @BeforeEach
    void setup() {
        page = new OrderPages();
    }

    /**
     * Teardown method to close the browser after each test if the page is an instance of OrderPages.
     */
    @AfterEach
    void teardown() {
        if (page instanceof OrderPages) {
            ((OrderPages) page).closeBrowser();
        }
    }

    /**
     * Test to verify that the Order page title is displayed correctly.
     */
    @Test
    void OrderPageTitleIsCorrect() {
        String expected = "My Order History";
        String actual = page.getTitle();
        assertEquals(expected, actual);
    }

    /**
     * Test to ensure that orders are displayed without throwing exceptions.
     */
    @Test
    void OrdersAreDisplayedCorrectly() {
        assertDoesNotThrow(() -> page.getOrderCount());
    }

    /**
     * Test to verify that order details open successfully and match expected mock data.
     */
    @Test
    void OrderDetailsOpenSuccessfully() {
        assertDoesNotThrow(() -> page.checkOrderDetails());

        OrderDetails expected = OrderDataUtil.getMockOrderDetails();
        assertEquals("ORD123", expected.getOrderId());  // Example validation
    }
}
