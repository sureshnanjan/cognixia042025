package herokuapp.tests;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTests {
    @Test
    void HomepageTitleisOK(){
        // Arrange
        // Launch HerokuApp
        String expected = "Welcome to the-internet1";
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

    }

    @Test
    void HomepageHasCorrectNumberOfExamples() {

    }
}
