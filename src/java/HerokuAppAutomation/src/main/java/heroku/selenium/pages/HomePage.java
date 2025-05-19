package heroku.selenium.pages;

import heroku.operations.ContextClickOperations;
import heroku.operations.HomePageOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import static utilities.WebElementInteractions.*;

import java.util.List;

public class HomePage implements HomePageOperations {
    private By titleLocator;
    private By subtitleLocator;
    private By exampleLocator;

    @FindBys({@FindBy(id = "foo"), @FindBy(className = "bar")})
    private By demoSupport;

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

        return getTextUtil(findElementUtil(this.titleLocator,this.browser));

    }

    @Override
    public String getSubTitle() {
        return getTextUtil(findElementUtil(this.subtitleLocator,this.browser));
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
    public Object goToExample(String exampleName) {
        navigateToLinkUtil(exampleName,this.browser);
        switch (exampleName){
            case "Context Menu":
                return  new ContextClick(this.browser);
            default:
                return null;

        }



    }
}
