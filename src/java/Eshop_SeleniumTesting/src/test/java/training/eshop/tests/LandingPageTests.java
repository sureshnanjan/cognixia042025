/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  JUnit 5 test class for Landing Page UI and functional tests for the eShop project.
 *  This includes verification of logos, banners, navigation links, filters, pagination,
 *  catalogue items visibility, product filtering, and footer presence.
 * =======================================================================================
 */

package training.eshop.tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import training.eshop.operations.*;
import training.eshop.seleniumpages.BasketPage;
import training.eshop.seleniumpages.LandingPage;
import training.eshop.utilities.*;

import java.util.Arrays;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LandingPageTests {

    private LandingPageOperations page;

    @BeforeEach
    void setUp() {
        page = new LandingPage();
    }

    @AfterEach
    void tearDown() {
        if (page instanceof LandingPage) {
            ((LandingPage) page).closeBrowser();
        }
    }

    /**
     * Verify that the logo image URL is as expected.
     */
    @Test
    void LogoDisplayisCorrect() {
        String expected = "/images/brand.png";
        EshopLogo actual_logo = page.getLogoDetails();
        assertEquals(expected, actual_logo.getImageUrl());
    }

    /**
     * Verify that the logo description text is as expected.
     */
    @Test
    void LogoDescriptionisCorrect() {
        String expected = "eShop On Web";
        EshopLogo actual_logo = page.getLogoDetails();
        assertEquals(expected, actual_logo.getImageDescription());
    }

    /**
     * Verify that the main banner image URL is correct.
     */
    @Test
    void BannerDisplayisCorrect() {
        String expected = "url(\"http://35.193.6.1:5106/images/main_banner.png\")";
        EshopBanner actual_banner = page.getBannerDetails();
        assertEquals(expected, actual_banner.getImageUrl());
    }

    /**
     * Verify that clicking the Login link navigates to the login page with correct title.
     */
    @Test
    void LoginLinkisCorrect() {
        String expected = "Log in";
        EshopOperations returnPage = page.enterLoginPage();
        String actualTitle = ((LoginPageOperations) returnPage).getTitle();
        assertEquals(expected, actualTitle);
    }

    /**
     * Verify that clicking the Basket link navigates to basket page showing "Basket is empty."
     */
    @Test
    void basketEmptyMessageIsCorrect() {
        String expected = "Basket is empty.";
        String actual = page.openBasketAndGetMessage();
        assertEquals(expected, actual);
    }


    /**
     * Verify the brand filter options are as expected.
     */
    @Test
    void BrandFilterIsCorrect() {
        List<String> expected = Arrays.asList("All", ".NET", "Azure", "Other", "SQL Server", "Visual Studio");
        BrandFilter actualFilter = page.getBrandFilter();
        List<String> actual = BrandFilter.getOptions(actualFilter.options);
        assertEquals(expected, actual);
    }

    /**
     * Verify the brand type filter options are as expected.
     */
    @Test
    void BrandTypeIsCorrect() {
        List<String> expected = Arrays.asList("All", "Mug", "Sheet", "T-Shirt", "USB Memory Stick");
        BrandType actualType = page.getBrandType();
        List<String> actual = BrandType.getOptions(actualType.options);
        assertEquals(expected, actual);
    }

    /**
     * Verify pagination controls are visible on the landing page.
     */
    @Test
    void testPaginationDetails() {
        PageNavigation navigation = page.getPaginationDetails();
        assertTrue(navigation.previousBtnVisible, "Previous button should be visible");
        assertTrue(navigation.nextBtnVisible, "Next button should be visible");
        assertTrue(navigation.showingTextVisible, "Showing text should be visible");
    }

    /**
     * Verify catalogue items are displayed with all relevant elements visible.
     */
    @Test
    void testCatalogueItemsDisplayed() {
        List<CatalogueItem> items = page.getCatalogueItems();
        assertFalse(items.isEmpty(), "Catalogue items should not be empty");
        for (CatalogueItem item : items) {
            assertNotNull(item.item_name, "Item name should not be null");
            assertTrue(item.imageVisible, "Item image should be visible");
            assertTrue(item.addToCartBtnVisible, "Add to basket button should be visible");
            assertTrue(item.priceVisible, "Item price should be visible");
        }
    }

    /**
     * Verify filtering products returns expected filtered product.
     */
    @Test
    void testFilterProducts() {
        CatalogueItem filteredItem = page.filterProducts();
        assertNotNull(filteredItem, "Filtered item should not be null");
        assertEquals("CUP<T> SHEET", filteredItem.item_name.toUpperCase(), "Filtered product name should match");
        assertTrue(filteredItem.nameVisible, "Filtered product name should be visible");
    }

    /**
     * Verify footer text or presence does not throw exceptions.
     */
    @Test
    void testFooterText() {
        assertDoesNotThrow(() -> page.getFooter());
    }
}
