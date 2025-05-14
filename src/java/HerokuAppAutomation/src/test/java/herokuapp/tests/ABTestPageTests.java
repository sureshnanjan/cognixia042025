package herokuapp.tests;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ABTestPageTests {
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
    void ABTestPageGivesNOABTestwhenCookieIsThere(){


    }

}
