package MyShuttle.Selenium.Pages;


import MyShuttle.Operations.DashboardPageOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WebElementInteractions;

public class DashboardPage implements DashboardPageOperations {

    private WebDriver driver;

    private By heading = By.tagName("h2");
    private By logo = By.cssSelector("img[src='Content/Images/logologin.png']");
    private By fareHistoryLink = By.cssSelector("body > div > section > div > div.panel-body > a");
    private By logoutLink = By.cssSelector("a[href='logout.jsp']");
//    private WebDriver driver1 = "http://34.93.0.171:8080/myshuttledev/home.jsp";

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public String getDashboardHeading() {
        WebElement headingElem = WebElementInteractions.findElementUtil(heading, driver);
        return WebElementInteractions.getTextUtil(headingElem);
    }

    @Override
    public boolean isLogoVisible() {
        WebElement logoElem = WebElementInteractions.findElementUtil(logo, driver);
        return WebElementInteractions.isElementDisplayed(logoElem);
    }

    @Override
    public void clickFareHistoryLink() {
        WebElement link = WebElementInteractions.findElementUtil(fareHistoryLink, driver);
        WebElementInteractions.clickElementUtil(link);
    }

    @Override
    public void logout() {
        WebElement logoutElem = WebElementInteractions.findElementUtil(logoutLink, driver);
        WebElementInteractions.clickElementUtil(logoutElem);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

//    @Override
//    public void goBackToPreviousPage() {
//        WebElementInteractions.navigateBackUtil(driver1);
//    }
}
