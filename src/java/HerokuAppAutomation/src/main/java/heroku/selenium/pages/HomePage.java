package heroku.selenium.pages;

import heroku.operations.HomePageOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class HomePage implements HomePageOperations {
    private By titleLocator;
    private By subtitleLocator;
    private By exampleLocator;

    private WebDriver browser;

    public HomePage() {
        this.exampleLocator = By.tagName("li");
        this.subtitleLocator = By.tagName("h2");
        this.titleLocator = By.tagName("h1");
        this.browser = new ChromeDriver();
        this.browser.get("https://the-internet.herokuapp.com/");
    }

    @Override
    public String getTitle() {
        return  this.browser.findElement(this.titleLocator).getText();
    }

    @Override
    public String getSubTitle() {
        return this.browser.findElement(this.subtitleLocator).getText();
    }

    @Override
    public List<WebElement> getAllExamples() {
        return this.browser.findElements(this.exampleLocator);
    }

    @Override
    public String getExampleName(int position) {
        List<WebElement> elements = this.browser.findElements(this.exampleLocator);
        if (position >= 0 && position < elements.size()) {
            String text = elements.get(position).getText();
            return text;
        } else {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }
    }

    @Override
    public void goToExample(String exampleName) {
        this.browser.findElement(this.exampleLocator).click();
    }

    }
