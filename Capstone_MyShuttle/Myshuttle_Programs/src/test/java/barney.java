import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class barney {
    public static void main(String[] args) throws Exception {
        //WebDriverManager.chromedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.get("http://34.93.0.171:8080/myshuttledev");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@id='email']")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("barney");
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.click();
        password.sendKeys("barneypassword");
        driver.findElement(By.xpath("//input[@value='Log in']")).click();

        //Assertions
        Thread.sleep(2000);
        WebElement dashboard = driver.findElement(By.tagName("h2"));
        System.out.println(dashboard.getText());
        String actualHeading = dashboard.getText();
        Assert.assertEquals(actualHeading,"Dashboard");

        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        Assert.assertTrue(currentUrl.contains("myshuttledev"));

        //image assertion
        WebElement logoImage = driver.findElement(By.cssSelector("img[src='Content/Images/logologin.png']"));
        Assert.assertTrue(logoImage.isDisplayed(), "Logo image is not displayed!");


        //Accessing the fare history link
        driver.findElement(By.cssSelector("body > div > section > div > div.panel-body > a")).click();
        //driver.findElement(By.xpath("//a[normalize-space()='Access Your Fare History']")).click();
        Thread.sleep(2000);
        //assertion for fred's fare
        WebElement fredfare = driver.findElement(By.tagName("h2"));
        String actualfredfare = fredfare.getText();
        Assert.assertEquals(actualfredfare,"Employee Fares for barney");

        //table column assertion
        List<String> expectedHeaders = Arrays.asList("ID", "Start", "Pickup", "End", "Dropoff", "Fare", "Driver", "Pass Rtg", "Drvr Rtg");

        List<WebElement> actualHeaderElements = driver.findElements(By.cssSelector("tr.info th"));
        List<String> actualHeaders = new ArrayList<>();
        for (WebElement th : actualHeaderElements) {
            actualHeaders.add(th.getText().trim());
        }

        Assert.assertEquals(actualHeaders, expectedHeaders);
        System.out.println("assertion successful for table");



        // Row 1 - First data row
        List<String> expectedRow1 = Arrays.asList(
                "19",
                "10:43:00 2014-12-13",
                "97 Stegasaurus St, Bedrock, WA",
                "11:20:00 2014-12-13",
                "93 Shale St, Bedrock, WA",
                "$ 18.69",
                "$ 14.01",
                "3",
                "2"
        );

        WebElement row1 = driver.findElement(By.cssSelector("body > div > section > div > div.panel-body > div > table > tbody > tr:nth-child(2)"));
        List<WebElement> row1Cells = row1.findElements(By.tagName("td"));

        List<String> actualRow1 = new ArrayList<>();
        for (WebElement cell : row1Cells) {
            actualRow1.add(cell.getText().trim());
        }

        Assert.assertEquals(actualRow1, expectedRow1);
        System.out.println("✅ Assertion passed for Row 1");

// Row 2 - Second data row
        List<String> expectedRow2 = Arrays.asList(
                "20",
                "10:41:00 2014-05-17",
                "43 Brontosaurus Blvd, Bedrock, WA",
                "10:53:00 2014-05-17",
                "51 Stegasaurus St, Rock Gardens, WA",
                "$ 14.6",
                "$ 10.95",
                "3",
                "2"
        );

        WebElement row2 = driver.findElement(By.cssSelector("body > div > section > div > div.panel-body > div > table > tbody > tr:nth-child(3)"));
        List<WebElement> row2Cells = row2.findElements(By.tagName("td"));

        List<String> actualRow2 = new ArrayList<>();
        for (WebElement cell : row2Cells) {
            actualRow2.add(cell.getText().trim());
        }

        Assert.assertEquals(actualRow2, expectedRow2);
        System.out.println("✅ Assertion passed for Row 2");



        //back navigation
        driver.navigate().back();

        //signout
        driver.findElement(By.cssSelector("a[href='logout.jsp']")).click();
        driver.quit();
    }
}

