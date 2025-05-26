/*
 * © 2025 eShop Testing Team. All rights reserved.
 * Authors: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 * This class represents the Error Page in the eShop web application.
 * It implements the EshopOperations interface to provide standardized
 * operations that can be performed on this page.
 */

package training.eshop.seleniumpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import training.eshop.operations.EshopOperations;
import training.eshop.utilities.WebElementInteractions;

public class ErrorPage implements EshopOperations {
    WebDriver driver;

    /**
     * Constructor to initialize the ErrorPage with a WebDriver instance.
     *
     * @param driver WebDriver instance to interact with the browser.
     */
    public ErrorPage(WebDriver driver) {
        this.driver = new ChromeDriver();
        WebElementInteractions.navigatetoUrlUtil("http://35.193.6.1:5106", this.driver);
        driver.manage().window().maximize();
    }

    @Override
    public String getTitle() {
        return "";
    }
}
