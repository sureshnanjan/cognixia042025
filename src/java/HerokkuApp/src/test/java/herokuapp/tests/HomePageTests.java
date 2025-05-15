package herokuapp.tests;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        String expected = "Available Examples";
        ChromeDriver driver = new ChromeDriver();
     
        driver.get("https://the-internet.herokuapp.com/");

        WebElement subTitle = driver.findElement(By.tagName("h2"));
        String actual = subTitle.getText();

        assertEquals(expected, actual, "Heroku Subtitle did not Match");
    }

    @Test
    void HomepageHasCorrectNumberOfExamples(){
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");

        List<WebElement> links = driver.findElements(By.tagName("li"));
        int linksSize = links.size();

        int expected = 44;
        int actual = linksSize;
        assertEquals(expected, actual, "Heroku Links count did not Match");
    }
}
