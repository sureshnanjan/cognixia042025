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
        String expected = "Available Examples";
        ChromeDriver mybrowser = new ChromeDriver();
        mybrowser.get("https://the-internet.herokuapp.com/");
        // Act
        //mybrowser.findElement(By.className("heading"));
        WebElement Subheading_element = mybrowser.findElement(By.tagName("h2"));
        String actual = Subheading_element.getText();
        // Assert
        assertEquals(expected, actual, "Heroku Title did not Match");
    }

    @Test
    void HomepageHasCorrectNumberOfExamples(){
        int expected = 44;
        ChromeDriver mybrowser = new ChromeDriver();
        mybrowser.get("https://the-internet.herokuapp.com/");
        WebElement ulElement = mybrowser.findElement(By.tagName("ul"));
        // Get all <li> inside that <ul>
        List<WebElement> listItems = ulElement.findElements(By.tagName("li"));
        int actual = listItems.size();
        System.out.println(actual);
        assertEquals(expected, actual, "Heroku No of examples did not Match");

    }
}
