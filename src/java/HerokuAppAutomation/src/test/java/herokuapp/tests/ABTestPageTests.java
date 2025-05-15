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
<<<<<<< HEAD
import org.openqa.selenium.Cookie;
=======
import org.openqa.selenium.Credentials;
import org.openqa.selenium.UsernameAndPassword;
>>>>>>> 0054286e1a9636c26bf6ea362f036c90db45d9cf
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
<<<<<<< HEAD
    void ABTestPageGivesOtherOptionsNoCookie() {
=======
    void Test1(){
>>>>>>> 0054286e1a9636c26bf6ea362f036c90db45d9cf
        // Arrange
        // Navigate to home Page
        // Check if cookie is not there or is set to false
        ChromeDriver mybrowser = new ChromeDriver();

<<<<<<< HEAD
        try {
            if (mybrowser.manage().getCookieNamed("optimizelyOptOut").getValue().compareToIgnoreCase("true") == 0) {
                mybrowser.manage().deleteCookieNamed("optimizelyOptOut");
            }
        } catch (NullPointerException ex) {
            System.out.println("Continue");
        }
=======
        CheckandRemoveOption(mybrowser, "optimizelyOptOut");
>>>>>>> 0054286e1a9636c26bf6ea362f036c90db45d9cf

        mybrowser.get("https://the-internet.herokuapp.com");
        mybrowser.findElement(By.linkText("A/B Testing")).click();
        // Go To AB Test Page
        String title = "NO A/B Test";
        String[] expected_titles = {"A/B Test Variation 1", "A/B Test Control"};
        // Act
        // Get The actual Title
        String actual = mybrowser.findElement(By.tagName("h3")).getText();

        // Assert
<<<<<<< HEAD
        assertNotEquals(title, actual);

    }

    @Test
    public void ABTestPageGivesNoABTestWhenCookieIsThere() {
        ChromeDriver myBrowser = new ChromeDriver();

        Cookie newCookie = new Cookie("optimizelyOptOut", "true");
        myBrowser.get("https://the-internet.herokuapp.com");

        try {
            myBrowser.manage().addCookie(newCookie);
        } catch (Exception ex) {
            System.out.println("Continue");
        }

        myBrowser.findElement(By.linkText("A/B Testing")).click();

        String expectedTitle = "No A/B Test";
        String actualTitle = myBrowser.findElement(By.tagName("h3")).getText();
        assertEquals(expectedTitle, actualTitle);

        myBrowser.quit();
    }
=======
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


>>>>>>> 0054286e1a9636c26bf6ea362f036c90db45d9cf
}

