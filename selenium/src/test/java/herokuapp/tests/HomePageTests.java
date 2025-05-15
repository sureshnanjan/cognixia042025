package herokuapp.tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTests {

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
    void HomepageTitleisOK() {
        String expected = "Welcome to the-internet";
        WebElement headingElement = mybrowser.findElement(By.tagName("h1"));
        String actual = headingElement.getText();
        assertEquals(expected, actual, "Heroku Title did not match");
    }

    @Test
    void HomepageSubTitleisOK() {
        String expected = "Available Examples";
        WebElement subheadingElement = mybrowser.findElement(By.tagName("h2"));
        String actual = subheadingElement.getText();
        assertEquals(expected, actual, "Heroku Subtitle did not match");
    }

    @Test
    void HomepageHasCorrectNumberOfExamples() {
        int expected = 44; // Update this if the number of examples changes
        WebElement ulElement = mybrowser.findElement(By.tagName("ul"));
        List<WebElement> listItems = ulElement.findElements(By.tagName("li"));
        int actual = listItems.size();
        System.out.println("Number of examples found: " + actual);
        assertEquals(expected, actual, "Heroku number of examples did not match");
    }

}


