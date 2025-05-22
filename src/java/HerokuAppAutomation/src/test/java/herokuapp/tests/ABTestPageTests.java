/*
* * ABTestPageTests
* * *
*  1.0 *
*
* * Copyright notice
*
* */
package herokuapp.tests;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Credentials;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.virtualauthenticator.Credential;

import java.util.Objects;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ABTestPageTests {
    /**
     * THis is Java documenattion for this test
     * */
    @Test
    void Test1(){
        // Arrange
        // Navigate to home Page
        // Check if cookie is not there or is set to false
        ChromeDriver mybrowser = new ChromeDriver();

        CheckandRemoveOption(mybrowser, "optimizelyOptOut");

        mybrowser.get("https://the-internet.herokuapp.com");
        mybrowser.findElement(By.linkText("A/B Testing")).click();
        // Go To AB Test Page
        String title = "NO A/B Test";
        String[] expected_titles = {"A/B Test Variation 1", "A/B Test Control"};
        // Act
        // Get The actual Title
        String actual = mybrowser.findElement(By.tagName("h3")).getText();

        // Assert
        //assertNotEquals(title,actual);
        //ABTestPageGivesNOABTestwhenCookieIsThere();

    }

    private static void CheckandRemoveOption(ChromeDriver mybrowser, String optimizelyOptOut) {
        try {
            if (mybrowser.manage().getCookieNamed(optimizelyOptOut)
                    .getValue().compareToIgnoreCase("true") == 0) {
                mybrowser.manage().deleteCookieNamed(optimizelyOptOut);
            }
        } catch (NullPointerException ex) {
            System.out.println("Continue");
        }
    }

    @Test
    void TestOptimizelyBucket(){
        // Arrange
        // Navigate to home Page
        // Check if cookie is not there or is set to false
        ChromeDriver mybrowser = new ChromeDriver();
        CheckandRemoveOption(mybrowser, "optimizelyBucket");
        mybrowser.get("https://the-internet.herokuapp.com");
        mybrowser.findElement(By.linkText("A/B Testing")).click();
        // Go To AB Test Page
        String title = "NO A/B Test";
        String[] expected_titles = {"A/B Test Variation 1", "A/B Test Control"};
        // Act
        // Get The actual Title
        String actual = mybrowser.findElement(By.tagName("h3")).getText();

        // Assert
        //assertNotEquals(title,actual);
        //ABTestPageGivesNOABTestwhenCookieIsThere();

    }

    @Test
    void TestOptimizelyEndUserID(){
        // Arrange
        // Navigate to home Page
        // Check if cookie is not there or is set to false
        ChromeDriver mybrowser = new ChromeDriver();
// The implementation for the Behaviour could change
// Cookie - Session State
        CheckandRemoveOption(mybrowser, "optimizelyEndUserId");

        mybrowser.get("https://the-internet.herokuapp.com");
        mybrowser.findElement(By.linkText("A/B Testing")).click();
        // Go To AB Test Page
        String title = "NO A/B Test";
        String[] expected_titles = {"A/B Test Variation 1", "A/B Test Control"};
        // Act
        // Get The actual Title
        String actual = mybrowser.findElement(By.tagName("h3")).getText();

        // Assert
        //assertNotEquals(title,actual);
        //ABTestPageGivesNOABTestwhenCookieIsThere();

    }


    @Test
    void ABTestPageGivesNOABTestwhenCookieIsThere(){
        Credential crds = (Credential) UsernameAndPassword.of("admin", "admin");
        ChromeDriver mybrowser = new ChromeDriver();
        mybrowser.register((Supplier<Credentials>) crds);

        //ABTestPageGivesOtherOptionsNoCookie();
    }
    @Test
    void ABTestingWhenOptOutnotEnabled(){
        //HomePage app = new HomePage();
        //app.OptOut();
        //app.GoTo("ABTesting");
        String expected = "NO AB Test";
        //String actual = app.getTitle();
        //assertNotEquals(expected, actual);
    }


}
