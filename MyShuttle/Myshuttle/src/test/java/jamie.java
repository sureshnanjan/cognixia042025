import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class jamie {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.get("http://34.93.0.171:8080/myshuttledev");
        driver.manage().window().maximize();

        // Login
        driver.findElement(By.id("email")).sendKeys("jamie");
        driver.findElement(By.id("password")).sendKeys("jamiepassword");
        driver.findElement(By.cssSelector("input[value='Log in']")).click();

        // Dashboard Assertion
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(), "Dashboard");
        Assert.assertTrue(driver.getCurrentUrl().contains("myshuttledev"));

        // Logo Assertion
        WebElement logoImage = driver.findElement(By.cssSelector("img[src='Content/Images/logologin.png']"));
        Assert.assertTrue(logoImage.isDisplayed(), "Logo image is not displayed!");

        // Access fare history
        driver.findElement(By.cssSelector("body > div > section > div > div.panel-body > a")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.tagName("h2")).getText(), "Employee Fares for jamie");

        // Table header assertion
        List<String> expectedHeaders = Arrays.asList("ID", "Start", "Pickup", "End", "Dropoff", "Fare", "Driver", "Pass Rtg", "Drvr Rtg");
        List<WebElement> actualHeaderElements = driver.findElements(By.cssSelector("tr.info th"));
        List<String> actualHeaders = new ArrayList<>();
        for (WebElement th : actualHeaderElements) {
            actualHeaders.add(th.getText().trim());
        }
        Assert.assertEquals(actualHeaders, expectedHeaders);
        System.out.println("✅ Header assertion passed");

        // Row ID 21
        List<String> expectedRow21 = Arrays.asList("21", "16:13:00 2014-06-19", "87 Wooly Way, Topsoil, WA", "16:34:00 2014-06-19", "55 Mammoth Way, Bedrock, WA", "$ 15.27", "$ 11.45", "3", "4");
        WebElement row21 = driver.findElement(By.cssSelector("tbody tr:nth-child(2)"));
        List<WebElement> cells21 = row21.findElements(By.tagName("td"));
        List<String> actualRow21 = new ArrayList<>();
        for (WebElement cell : cells21) actualRow21.add(cell.getText().trim());
        Assert.assertEquals(actualRow21, expectedRow21);
        System.out.println("✅ Header assertion passed for Row ID 21");

// Row ID 22
        List<String> expectedRow22 = Arrays.asList("22", "17:00:00 2015-01-20", "18 Wooly Way, Bedrock, WA", "17:02:00 2015-01-20", "73 Mammoth Way, Limestone, WA", "$ 7.43", "$ 5.57", "2", "3");
        WebElement row22 = driver.findElement(By.cssSelector("tbody tr:nth-child(3)"));
        List<WebElement> cells22 = row22.findElements(By.tagName("td"));
        List<String> actualRow22 = new ArrayList<>();
        for (WebElement cell : cells22) actualRow22.add(cell.getText().trim());
        Assert.assertEquals(actualRow22, expectedRow22);
        System.out.println("✅ Header assertion passed for Row ID 22");

// Row ID 23
        List<String> expectedRow23 = Arrays.asList("23", "00:20:00 2015-04-28", "42 Mammoth Way, Rock Gardens, WA", "00:40:00 2015-04-28", "71 Hunting Lane, Bedrock, WA", "$ 16.82", "$ 12.61", "5", "2");
        WebElement row23 = driver.findElement(By.cssSelector("tbody tr:nth-child(4)"));
        List<WebElement> cells23 = row23.findElements(By.tagName("td"));
        List<String> actualRow23 = new ArrayList<>();
        for (WebElement cell : cells23) actualRow23.add(cell.getText().trim());
        Assert.assertEquals(actualRow23, expectedRow23);
        System.out.println("✅ Header assertion passed for Row ID 23");

// Row ID 24
        List<String> expectedRow24 = Arrays.asList("24", "04:32:00 2014-08-26", "25 Hunting Lane, Topsoil, WA", "04:38:00 2014-08-26", "4 Mammoth Way, Limestone, WA", "$ 9.0", "$ 6.75", "1", "2");
        WebElement row24 = driver.findElement(By.cssSelector("tbody tr:nth-child(5)"));
        List<WebElement> cells24 = row24.findElements(By.tagName("td"));
        List<String> actualRow24 = new ArrayList<>();
        for (WebElement cell : cells24) actualRow24.add(cell.getText().trim());
        Assert.assertEquals(actualRow24, expectedRow24);
        System.out.println("✅ Header assertion passed for Row ID 24");

// Row ID 25
        List<String> expectedRow25 = Arrays.asList("25", "22:58:00 2014-11-05", "1 Wooly Way, Rock Gardens, WA", "23:02:00 2014-11-05", "19 Wooly Way, Rock Gardens, WA", "$ 7.18", "$ 5.38", "2", "1");
        WebElement row25 = driver.findElement(By.cssSelector("tbody tr:nth-child(6)"));
        List<WebElement> cells25 = row25.findElements(By.tagName("td"));
        List<String> actualRow25 = new ArrayList<>();
        for (WebElement cell : cells25) actualRow25.add(cell.getText().trim());
        Assert.assertEquals(actualRow25, expectedRow25);
        System.out.println("✅ Header assertion passed for Row ID 25");

// Row ID 26
        List<String> expectedRow26 = Arrays.asList("26", "07:04:00 2014-05-20", "36 Hard Rock Pl, Rock Gardens, WA", "07:26:00 2014-05-20", "38 Shale St, Limestone, WA", "$ 5.67", "$ 4.25", "4", "2");
        WebElement row26 = driver.findElement(By.cssSelector("tbody tr:nth-child(7)"));
        List<WebElement> cells26 = row26.findElements(By.tagName("td"));
        List<String> actualRow26 = new ArrayList<>();
        for (WebElement cell : cells26) actualRow26.add(cell.getText().trim());
        Assert.assertEquals(actualRow26, expectedRow26);
        System.out.println("✅ Header assertion passed for Row ID 26");

// Row ID 27
        List<String> expectedRow27 = Arrays.asList("27", "16:00:00 2014-04-08", "37 Hunting Lane, Rock Gardens, WA", "16:24:00 2014-04-08", "83 Brontosaurus Blvd, Topsoil, WA", "$ 28.78", "$ 21.58", "1", "5");
        WebElement row27 = driver.findElement(By.cssSelector("tbody tr:nth-child(8)"));
        List<WebElement> cells27 = row27.findElements(By.tagName("td"));
        List<String> actualRow27 = new ArrayList<>();
        for (WebElement cell : cells27) actualRow27.add(cell.getText().trim());
        Assert.assertEquals(actualRow27, expectedRow27);
        System.out.println("✅ Header assertion passed for Row ID 27");

// Row ID 28
        List<String> expectedRow28 = Arrays.asList("28", "01:18:00 2014-05-06", "84 Mammoth Way, Bedrock, WA", "01:29:00 2014-05-06", "40 Stegasaurus St, Limestone, WA", "$ 19.1", "$ 14.32", "5", "1");
        WebElement row28 = driver.findElement(By.cssSelector("tbody tr:nth-child(9)"));
        List<WebElement> cells28 = row28.findElements(By.tagName("td"));
        List<String> actualRow28 = new ArrayList<>();
        for (WebElement cell : cells28) actualRow28.add(cell.getText().trim());
        Assert.assertEquals(actualRow28, expectedRow28);
        System.out.println("✅ Header assertion passed for Row ID 28");

// Row ID 29
        List<String> expectedRow29 = Arrays.asList("29", "10:43:00 2014-12-13", "97 Stegasaurus St, Bedrock, WA", "11:20:00 2014-12-13", "93 Shale St, Bedrock, WA", "$ 18.69", "$ 14.01", "3", "2");
        WebElement row29 = driver.findElement(By.cssSelector("tbody tr:nth-child(10)"));
        List<WebElement> cells29 = row29.findElements(By.tagName("td"));
        List<String> actualRow29 = new ArrayList<>();
        for (WebElement cell : cells29) actualRow29.add(cell.getText().trim());
        Assert.assertEquals(actualRow29, expectedRow29);
        System.out.println("✅ Header assertion passed for Row ID 29");

// Row ID 30
        List<String> expectedRow30 = Arrays.asList("30", "10:41:00 2014-05-17", "43 Brontosaurus Blvd, Bedrock, WA", "10:53:00 2014-05-17", "51 Stegasaurus St, Rock Gardens, WA", "$ 14.6", "$ 10.95", "3", "2");
        WebElement row30 = driver.findElement(By.cssSelector("tbody tr:nth-child(11)"));
        List<WebElement> cells30 = row30.findElements(By.tagName("td"));
        List<String> actualRow30 = new ArrayList<>();
        for (WebElement cell : cells30) actualRow30.add(cell.getText().trim());
        Assert.assertEquals(actualRow30, expectedRow30);
        System.out.println("✅ Header assertion passed for Row ID 30");


        // Back and logout
        driver.navigate().back();
        driver.findElement(By.cssSelector("a[href='logout.jsp']")).click();
        driver.quit();
    }
}
