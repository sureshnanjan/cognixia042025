/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class provides functionality to verify the footer text on the eShop website.
 *  It checks if the footer contains the expected copyright text.
 * =======================================================================================
 */

package training.eshop.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Footer {

    private WebDriver driver;

    /**
     * Constructor to initialize Footer with a WebDriver instance.
     */
    public Footer() {
        this.driver = driver;
    }

    /**
     * Checks if the given footer element contains the expected copyright text.
     * @param element WebElement representing the footer text element
     */
    public void checkFooterText(WebElement element) {
        String text = element.getText();
        String expectedText = "e-ShopOnWeb. All rights reserved";
        if (text.equals(expectedText)) {
            System.out.println("Footer found.");
        } else {
            System.out.println("Footer not found. Found text: " + text);
        }
    }
}
