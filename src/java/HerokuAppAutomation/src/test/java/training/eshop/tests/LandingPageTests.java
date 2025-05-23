package training.eshop.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import training.eshop.operations.LandingPageOperations;
import training.eshop.seleniumpages.LandingPage;
import training.eshop.utilities.BrandFilter;
import training.eshop.utilities.CatalogueItem;
import training.eshop.utilities.EshopLogo;
import training.eshop.utilities.PageNavigation;

import java.util.List;

public class LandingPageTests {
    @Test
    void LogoDisplayisCorrect(){
        /// AAA
        String expected = "brand.png";
        // SUT / AUT
        LandingPageOperations application = new LandingPage();
        EshopLogo actual_logo = application.getLogoDetails();
        assertEquals(expected, actual_logo.getImageUrl());

    }







    @Test
    void ProductsPaginationIsCorrect2(){
        /// AAA
        final int PRODUCTS_PER_PAGE = 6;
        // SUT / AUT
        LandingPageOperations application = new LandingPage();
        List<CatalogueItem> actual_products = application.getCatalogueItems();
        int expected_total_pages = actual_products.size() / PRODUCTS_PER_PAGE;
        //String actual_total_pages = application.getPaginationDeatils();
        // Assert that there is 3 pages

    }

    @Test
    void ProductsPaginationIsCorrect(){
        /// AAA
        final int PRODUCTS_PER_PAGE = 6;
        // SUT / AUT
        LandingPageOperations application = new LandingPage();
        List<CatalogueItem> actual_products = application.getCatalogueItems();
        int expected_total_pages = actual_products.size() / PRODUCTS_PER_PAGE;
        PageNavigation actual_total_pages = application.getPaginationDeatils();
        // Assert that there is 3 pages

    }

    @Test
    void ProductsFilteringIsCorrect(){
        String[] expected_items = {"Van Huesen", "Arrow", "Peter Englnd"};
        LandingPageOperations application = new LandingPage();
        BrandFilter filter = application.getBrandFilter();
        //filter.getOptions().
        //PageNavigation actual_total_pages = application.getPaginationDeatils();
        // Assert that there is 3 pages

    }

}
