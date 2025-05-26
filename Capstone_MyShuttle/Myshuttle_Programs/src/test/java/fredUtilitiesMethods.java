import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.WebElementInteractions;

import java.util.Arrays;
import java.util.List;

public class fredUtilitiesMethods {

    WebDriver driver;

    @Test (priority=1)
    public void setUp() {
        driver = new ChromeDriver();
        WebElementInteractions.navigatetoUrlUtil("http://34.93.0.171:8080/myshuttledev", driver);
    }

    @Test(priority=2)
    public void loginTest() throws InterruptedException {
        setUp();
        WebElementInteractions.sendKeysUtil(By.id("email"), driver, "fred");
        WebElementInteractions.sendKeysUtil(By.id("password"), driver, "fredpassword");
        WebElementInteractions.clickElementUtil(WebElementInteractions.findElementUtil(By.xpath("//input[@value='Log in']"), driver));
        Thread.sleep(2000);

        WebElement dashboard = WebElementInteractions.findElementUtil(By.tagName("h2"), driver);
        Assert.assertEquals(WebElementInteractions.getTextUtil(dashboard), "Dashboard");

        String currentUrl = WebElementInteractions.getCurrentUrlUtil(driver);
        Assert.assertTrue(currentUrl.contains("myshuttledev"));

        WebElement logoImage = WebElementInteractions.findElementUtil(By.cssSelector("img[src='Content/Images/logologin.png']"), driver);
        Assert.assertTrue(logoImage.isDisplayed());

    }

    @Test(priority=3)
    public void fareHistoryTest() throws InterruptedException {
        loginTest();  // login first
        WebElementInteractions.clickElementUtil(WebElementInteractions.findElementUtil(By.cssSelector("body > div > section > div > div.panel-body > a"), driver));
        Thread.sleep(2000);

        WebElement fareHeading = WebElementInteractions.findElementUtil(By.tagName("h2"), driver);
        System.out.println(fareHeading);
        Assert.assertEquals(WebElementInteractions.getTextUtil(fareHeading), "Employee Fares for fred");

        List<String> expectedHeaders = Arrays.asList("ID", "Start", "Pickup", "End", "Dropoff", "Fare", "Driver", "Pass Rtg", "Drvr Rtg");
        List<WebElement> headerElements = driver.findElements(By.cssSelector("tr.info th"));
        List<String> actualHeaders = headerElements.stream().map(WebElement::getText).map(String::trim).toList();
        Assert.assertEquals(actualHeaders, expectedHeaders);

        // Row 1 check
        WebElement row1 = WebElementInteractions.findElementUtil(By.cssSelector("tbody > tr:nth-child(2)"), driver);
        List<String> expectedRow1 = Arrays.asList("17", "16:00:00 2014-04-08", "37 Hunting Lane, Rock Gardens, WA",
                "16:24:00 2014-04-08", "83 Brontosaurus Blvd, Topsoil, WA", "$ 28.78", "$ 21.58", "1", "5");
        Assert.assertEquals(WebElementInteractions.getTableRowText(row1), expectedRow1);

    }

    @Test(priority=4)
    public void loginFail() throws InterruptedException{
        setUp();
        WebElementInteractions.sendKeysUtil(By.id("email"), driver, "fred1");
        WebElementInteractions.sendKeysUtil(By.id("password"), driver, "fredpassword");
        WebElementInteractions.clickElementUtil(WebElementInteractions.findElementUtil(By.xpath("//input[@value='Log in']"), driver));

        WebElement wrngcrendentials = WebElementInteractions.findElementUtil(By.tagName("h2"), driver);
        Assert.assertEquals(WebElementInteractions.getTextUtil(wrngcrendentials), "Sorry, please back up and try again");
    }

    @Test(priority=5)
    public void tearDown() throws InterruptedException {
        fareHistoryTest();
        driver.navigate().back();
        WebElementInteractions.clickElementUtil(WebElementInteractions.findElementUtil(By.cssSelector("a[href='logout.jsp']"), driver));
        Thread.sleep(2000);
        driver.quit();
    }
}
