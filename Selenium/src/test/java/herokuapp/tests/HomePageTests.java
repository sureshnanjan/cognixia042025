package herokuapp.tests;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTests {
    @Test
    void HomepageTitleisOK(){
        // Arrange
        // Launch HerokuApp
        String expected = "Welcome to the-internet";
        ChromeDriver mybrowser = new ChromeDriver();
        mybrowser.get("https://the-internet.herokuapp.com/");
        // Act
        //mybrowser.findElement(By.className("heading"));
        WebElement heading_element = mybrowser.findElement(By.tagName("h1"));
        String actual = heading_element.getText();
        // Assert
        assertEquals(expected, actual, "Heroku Title did not Match");

    }

    @Test
    void HomepageSubTitleisOK(){
        // Arrange
        String expected = "Available Examples";
        ChromeDriver mybrowser = new ChromeDriver();
        mybrowser.get("https://the-internet.herokuapp.com/");
        // Act
        WebElement subheading_element = mybrowser.findElement(By.tagName("h2"));
        String actual = subheading_element.getText();
        // Assert
        assertEquals(expected, actual, "Heroku SubTitle did not Match");

    }

    @Test
    void HomepageHasCorrectNumberOfExamples(){
        // Arrange
        int expected = 44;
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        // Act
        List<WebElement> links = driver.findElements(By.tagName("li"));
        int actual = links.size();
        // Assert
        assertEquals(expected, actual, "Heroku Links count did not Match");
    }
}
