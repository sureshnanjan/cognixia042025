package herokuapp.tests.withpo;

import heroku.selenium.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginPageTests {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeAll
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://34.93.0.171:8080/myshuttledev");
        loginPage = new LoginPage(driver);
    }

    @Test
    void validLoginTest() {
        loginPage.enterEmail("fred");
        loginPage.enterPassword("fredpassword");
        loginPage.clickLogin();

        assertTrue(loginPage.isLoginSuccessful(), "Login should be successful with valid credentials");
    }

    @Test
    void invalidLoginTest() {
        loginPage.enterEmail("invalid");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLogin();

        String errorMsg = loginPage.getLoginErrorMessage();
        assertNotNull(errorMsg);
        assertFalse(errorMsg.isEmpty(), "Error message should be displayed for invalid login");
    }

    @AfterAll
    void teardown() {
        driver.quit();
    }
}
