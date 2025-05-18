package heroku.selenium.pages;

import heroku.operations.HomePageOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static utilities.WebElementInteractions.*;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class HomePage implements HomePageOperations {
    private By titleLocator;
    private By subtitleLocator;
    private By exampleLocator;

    private WebDriver browser;

    public HomePage() {
        this.exampleLocator = By.tagName("li");
        this.subtitleLocator = By.tagName("h2");
        this.titleLocator = By.tagName("h1");
        this.browser = new FirefoxDriver();
        this.browser.get("https://the-internet.herokuapp.com/");
    }

    @Override
    public String getTitle() {

        return getTextUtil(findElementUtil(this.titleLocator,this.browser));

    }

    @Override
    public String getSubTitle() {
        return getTextUtil(findElementUtil(this.subtitleLocator,this.browser));
       // return this.browser.findElement(this.subtitleLocator).getText();
    }

    @Override
    public List<WebElement> getAllExamples() {
        return this.browser.findElements(this.exampleLocator);
    }

    @Override
    public String getExampleName(int position) {
        return this.browser.findElement(this.exampleLocator).getText();
    }

    @Override
    public void goToExample(String exampleName) {
        this.browser.findElement(this.exampleLocator).click();
    }
}
