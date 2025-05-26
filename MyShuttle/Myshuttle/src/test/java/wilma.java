import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class wilma {
    public static void main(String[] args) throws Exception {
        //WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://34.93.0.171:8080/myshuttledev");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@id='email']")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("wilma");
        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.click();
        password.sendKeys("wilmapassword");
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
        Assert.assertEquals(actualfredfare,"Employee Fares for wilma");

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
                "14",
                "04:32:00 2014-08-26",
                "25 Hunting Lane, Topsoil, WA",
                "04:38:00 2014-08-26",
                "4 Mammoth Way, Limestone, WA",
                "$ 9.0",
                "$ 6.75",
                "1",
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
                "15",
                "22:58:00 2014-11-05",
                "1 Wooly Way, Rock Gardens, WA",
                "23:02:00 2014-11-05",
                "19 Wooly Way, Rock Gardens, WA",
                "$ 7.18",
                "$ 5.38",
                "2",
                "1"
        );

        WebElement row2 = driver.findElement(By.cssSelector("body > div > section > div > div.panel-body > div > table > tbody > tr:nth-child(3)"));
        List<WebElement> row2Cells = row2.findElements(By.tagName("td"));

        List<String> actualRow2 = new ArrayList<>();
        for (WebElement cell : row2Cells) {
            actualRow2.add(cell.getText().trim());
        }

        Assert.assertEquals(actualRow2, expectedRow2);
        System.out.println("✅ Assertion passed for Row 2");

// Row 3 - third data row
        List<String> expectedRow3 = Arrays.asList(
                "16",
                "07:04:00 2014-05-20",
                "36 Hard Rock Pl, Rock Gardens, WA",
                "07:26:00 2014-05-20",
                "38 Shale St, Limestone, WA",
                "$ 5.67",
                "$ 4.25",
                "4",
                "2"
        );

        WebElement row3 = driver.findElement(By.cssSelector("body > div > section > div > div.panel-body > div > table > tbody > tr:nth-child(4)"));
        List<WebElement> row3Cells = row3.findElements(By.tagName("td"));

        List<String> actualRow3 = new ArrayList<>();
        for (WebElement cell : row3Cells) {
            actualRow3.add(cell.getText().trim());
        }

        Assert.assertEquals(actualRow3, expectedRow3);
        System.out.println("✅ Assertion passed for Row 3");

        //back navigation
        driver.navigate().back();

        //signout
        driver.findElement(By.cssSelector("a[href='logout.jsp']")).click();
        driver.quit();
    }
}

