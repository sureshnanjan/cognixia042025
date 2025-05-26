package heroku.selenium.pages;

//import heroku.selenium.pages.FareHistoryPage;
import heroku.selenium.pages.LoginPage;
//import heroku.selenium.pages.DashboardPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FareHistoryPageTests {

    private WebDriver driver;
    private LoginPage loginPage;
    //private DashboardPage dashboardPage;
    //private FareHistoryPage fareHistoryPage;

    @BeforeAll
    void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://34.93.0.171:8080/myshuttledev");

        loginPage = new LoginPage(driver);
        loginPage.enterEmail("fred");
        loginPage.enterPassword("fredpassword");
        loginPage.clickLogin();

        dashboardPage = new DashboardPage(driver);
        dashboardPage.goToFareHistory();

        fareHistoryPage = new FareHistoryPage(driver);

        // Small wait to ensure page loads
        Thread.sleep(2000);
    }

    @Test
    void pageHeadingTest() {
        String heading = fareHistoryPage.getPageHeading();
        assertTrue(heading.contains("Employee Fares"));
    }

    @Test
    void tableHeadersTest() {
        List<String> expectedHeaders = Arrays.asList("ID", "Start", "Pickup", "End", "Dropoff", "Fare", "Driver", "Pass Rtg", "Drvr Rtg");
        List<String> actualHeaders = fareHistoryPage.getTableHeaders();

        assertEquals(expectedHeaders, actualHeaders);
    }

    @Test
    void fareRowsContentTest() {
        List<List<String>> rows = fareHistoryPage.getFareRows();
        assertFalse(rows.isEmpty(), "Fare rows should not be empty");
        // Optional: Add detailed row content asserts if you want
    }

    @Test
    void backAndLogoutTest() {
        fareHistoryPage.goBackToDashboard();
        assertTrue(driver.getCurrentUrl().contains("myshuttledev"));

        fareHistoryPage.logout();
        assertTrue(driver.getCurrentUrl().contains("logout") || driver.getCurrentUrl().contains("login"));
    }

    @AfterAll
    void teardown() {
        driver.quit();
    }
}
