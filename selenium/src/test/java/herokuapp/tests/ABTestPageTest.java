package herokuapp.tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ABTestPageTest {
    private ChromeDriver mybrowser;

    @BeforeEach
    void setUp() {
        mybrowser = new ChromeDriver();
        mybrowser.get("https://the-internet.herokuapp.com/");
    }

    @AfterEach
    void tearDown() {
        if (mybrowser != null) {
            mybrowser.quit();
        }
    }
    @Test
    void ABTestPageGivesOtherOptionsNoCookie(){
        // Arrange
        // Navigate to home Page
        // Check if cookie is not there or is set to false
        ChromeDriver mybrowser = new ChromeDriver();
        try {
            if (mybrowser.manage().getCookieNamed("optimizelyOptOut").getValue().compareToIgnoreCase("true") == 0) {
                mybrowser.manage().deleteCookieNamed("optimizelyOptOut");
            }
        }catch(NullPointerException ex){
            System.out.println("Continue");
        }
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

    }

    @Test
    void ABTestPageGivesNoABTestWhenCookieIsThere() {
        // Add the opt-out cookie
        Cookie optOutCookie = new Cookie("optimizelyOptOut", "true");
        try {
            mybrowser.manage().addCookie(optOutCookie);
        } catch (Exception ex) {
            System.out.println("Could not add cookie, continuing test...");
        }
        // Refresh to apply cookie before clicking the link
        mybrowser.navigate().refresh();
        // Navigate to A/B Testing page
        mybrowser.findElement(By.linkText("A/B Testing")).click();
        // Verify the title
        String expected = "No A/B Test";
        String actual = mybrowser.findElement(By.tagName("h3")).getText();
        assertEquals(expected, actual, "Expected 'No A/B Test' when opt-out cookie is set");
    }
}