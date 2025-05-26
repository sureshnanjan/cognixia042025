package MyShuttleApp.tests;

import MyShuttle.Selenium.Pages.DashboardPage;
import MyShuttle.Selenium.Pages.FareHistoryPage;
import MyShuttle.Selenium.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class FareHistoryPageTests {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    FareHistoryPage fareHistoryPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("fred");
        loginPage.enterPassword("fredpassword");
        loginPage.clickLogin();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dashboardPage = new DashboardPage(driver);
        dashboardPage.clickFareHistoryLink();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fareHistoryPage = new FareHistoryPage(driver);
    }

    @Test
    public void testFareHistoryHeading() {
        String heading = fareHistoryPage.getFareHistoryHeading();
        Assert.assertTrue(heading.contains("Employee Fares for fred"));
    }

    @Test
    public void testTableHeaders() {
        List<String> expectedHeaders = List.of("ID", "Start", "Pickup", "End", "Dropoff", "Fare", "Driver", "Pass Rtg", "Drvr Rtg");
        List<String> actualHeaders = fareHistoryPage.getTableHeaders();
        Assert.assertEquals(actualHeaders, expectedHeaders);
    }

    @Test
    public void testFirstTwoRowsData() {
        List<String> expectedRow1 = fareHistoryPage.expectedRow1();
        List<String> expectedRow2 = fareHistoryPage.expectedRow2();

        List<String> actualRow1 = fareHistoryPage.getTableRowData(1);
        List<String> actualRow2 = fareHistoryPage.getTableRowData(2);

        Assert.assertEquals(actualRow1, expectedRow1);
        Assert.assertEquals(actualRow2, expectedRow2);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            fareHistoryPage.goBack();
            dashboardPage = new DashboardPage(driver);
            dashboardPage.logout();
            driver.quit();
        }
    }
}
