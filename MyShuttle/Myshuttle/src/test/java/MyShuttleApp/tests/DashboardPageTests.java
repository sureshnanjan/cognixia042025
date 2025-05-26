package MyShuttleApp.tests;

import MyShuttle.Selenium.Pages.DashboardPage;
import MyShuttle.Selenium.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DashboardPageTests {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("fred");
        loginPage.enterPassword("fredpassword");
        loginPage.clickLogin();

        // Wait for Dashboard to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dashboardPage = new DashboardPage(driver);
    }

    @Test
    public void testDashboardHeading() {
        String heading = dashboardPage.getDashboardHeading();
        Assert.assertEquals(heading, "Dashboard");
    }

    @Test
    public void testLogoIsVisible() {
        Assert.assertTrue(dashboardPage.isLogoVisible(), "Logo should be visible on dashboard");
    }

    @Test
    public void testFareHistoryNavigation() {
        dashboardPage.clickFareHistoryLink();

        // Wait for Fare History page
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = dashboardPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("home.jsp") || currentUrl.contains("farehistory"), "URL should contain FareHistory");
        driver.navigate().back();
    }




    @AfterClass
    public void teardown() {
        if (driver != null) {
            dashboardPage.logout();
            driver.quit();
        }
    }
}
