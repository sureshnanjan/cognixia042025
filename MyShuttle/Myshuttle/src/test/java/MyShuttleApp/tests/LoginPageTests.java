package MyShuttleApp.tests;

import MyShuttle.Selenium.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTests {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginPageTitle() {
        String title = loginPage.getLoginPageTitle();
        System.out.println("Actual title is "+ title);
        Assert.assertTrue(title.contains("MyShuttle"), "Login page title check");


//        driver.get("http://34.93.0.171:8080/myshuttledev/");
//        // Get and print the title
//        String pageTitle = driver.getTitle();
//        System.out.println("Page Title: " + pageTitle);
    }

    @Test
    public void testSuccessfulLogin() {

        loginPage.enterEmail("fred");
        loginPage.enterPassword("fredpassword");
        loginPage.clickLogin();

        // Sleep briefly to wait for navigation (better to use waits in real tests)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("myshuttledev"), "URL should contain myshuttledev after login");
    }

    @AfterClass
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }
}

