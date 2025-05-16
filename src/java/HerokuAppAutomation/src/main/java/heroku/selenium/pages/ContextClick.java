package heroku.selenium.pages;

import heroku.operations.ContextClickOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import utilities.WebElementInteractions;

public class ContextClick implements ContextClickOperations {
    public ContextClick(WebDriver _driver) {
        this.driver = _driver;
        this.hotspotboxlocator = By.id("hot-spot");
    }
    private WebDriver driver;
    @FindBys(@FindBy(tagName="h3"))
    private By titleLocator;

    @FindBys(@FindBy(xpath="//*[@id='content']/div/p[2]/text()"))
    private By firstParagraphlocator; //

    @FindBys(@FindBy(id = "hot-spot"))
    private By hotspotboxlocator;


    @Override
    public String getTitle() {
        return WebElementInteractions.getTextUtil(
                WebElementInteractions.findElementUtil(titleLocator,this.driver));
    }

    @Override
    public String getParagraph(int pos) {
        return null;
    }

    @Override
    public void doHotSpotInvoke() {

        WebElementInteractions.RightClickUtil(
                WebElementInteractions.
                        findElementUtil(this.hotspotboxlocator,this.driver), this.driver);

    }

    @Override
    public String getResultTitle() {
        return null;
    }

    @Override
    public String getResultText() {
        return WebElementInteractions.getAlertText(this.driver);
    }
}
