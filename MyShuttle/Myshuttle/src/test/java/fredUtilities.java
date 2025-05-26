import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import utilities.WebElementInteractions;

import java.util.Arrays;
import java.util.List;

public class fredUtilities {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();

        WebElementInteractions.navigatetoUrlUtil("http://34.93.0.171:8080/myshuttledev", driver);



        // Login
        WebElementInteractions.sendKeysUtil(By.id("email"), driver, "fred");
        WebElementInteractions.sendKeysUtil(By.id("password"), driver, "fredpassword");
        WebElementInteractions.clickElementUtil(WebElementInteractions.findElementUtil(By.xpath("//input[@value='Log in']"), driver));

        Thread.sleep(2000);  // Use explicit wait in real tests




        // Assertions
        // Checking dashboard element present ot not after login
        WebElement dashboard = WebElementInteractions.findElementUtil(By.tagName("h2"), driver);
        System.out.println(dashboard);
        Assert.assertEquals(WebElementInteractions.getTextUtil(dashboard), "Dashboard", "did not match expected output");



        // Checking whether path is present or not
        String currentUrl = WebElementInteractions.getCurrentUrlUtil(driver);
        System.out.println("current url is "+currentUrl);
        Assert.assertTrue(currentUrl.contains("myshuttledev"),"did not match expected url");




        // Checking logo element is present or not.
        WebElement logoImage = WebElementInteractions.findElementUtil(By.cssSelector("img[src='Content/Images/logologin.png']"), driver);
        Assert.assertTrue(logoImage.isDisplayed(), "Logo image is not displayed!");




        // Fare history of fred
        WebElementInteractions.clickElementUtil(WebElementInteractions.findElementUtil(By.cssSelector("body > div > section > div > div.panel-body > a"), driver));
        Thread.sleep(2000);




        // Using Assertions to check Employee Fares for fred
        WebElement fredfare = WebElementInteractions.findElementUtil(By.tagName("h2"), driver);
        Assert.assertEquals(WebElementInteractions.getTextUtil(fredfare), "Employee Fares for fred");




        List<String> expectedHeaders = Arrays.asList("ID", "Start", "Pickup", "End", "Dropoff", "Fare", "Driver", "Pass Rtg", "Drvr Rtg");
        List<WebElement> actualHeaderElements = driver.findElements(By.cssSelector("tr.info th"));
        List<String> actualHeaders = actualHeaderElements.stream().map(WebElement::getText).map(String::trim).toList();
        Assert.assertEquals(actualHeaders, expectedHeaders);




        // Row1  assertions
        WebElement row1 = WebElementInteractions.findElementUtil(By.cssSelector("tbody > tr:nth-child(2)"), driver);
        List<String> expectedRow1 = Arrays.asList("17", "16:00:00 2014-04-08", "37 Hunting Lane, Rock Gardens, WA",
                "16:24:00 2014-04-08", "83 Brontosaurus Blvd, Topsoil, WA", "$ 28.78", "$ 21.58", "1", "5");
        Assert.assertEquals(WebElementInteractions.getTableRowText(row1), expectedRow1);



        // Row2 assertions
        WebElement row2 = WebElementInteractions.findElementUtil(By.cssSelector("tbody > tr:nth-child(3)"), driver);
        List<String> expectedRow2 = Arrays.asList("18", "01:18:00 2014-05-06", "84 Mammoth Way, Bedrock, WA",
                "01:29:00 2014-05-06", "40 Stegasaurus St, Limestone, WA", "$ 19.1", "$ 14.32", "5", "1");
        Assert.assertEquals(WebElementInteractions.getTableRowText(row2), expectedRow2);



        // Navigation & signout
        driver.navigate().back();
        WebElementInteractions.clickElementUtil(WebElementInteractions.findElementUtil(By.cssSelector("a[href='logout.jsp']"), driver));
        Thread.sleep(2000);
        driver.quit();
    }
}
