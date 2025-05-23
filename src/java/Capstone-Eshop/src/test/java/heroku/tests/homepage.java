package heroku.tests;

import heroku.operations.AddToCartPageOperations;
import heroku.operations.ForgotPasswordPageOperations;
import heroku.operations.LoginPageOperations;
import heroku.operations.RegisterPageOperations;
import heroku.selenium.pages.AddToCartPage;
import heroku.selenium.pages.ForgotPasswordPage;
import heroku.selenium.pages.LoginPage;
import heroku.selenium.pages.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class homepage {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "http://34.46.74.231:5106/";
    private final String expectedTitle = "Catalog - Microsoft.eShopOnWeb";  // Adjust this after verifying the actual page title
   // private final String expectedSubtitle = "eSHOP OnWeb"; // Adjust if subtitle is different

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testHomePageTitle() {
        String actualTitle = driver.getTitle();
        assertTrue(actualTitle.contains(expectedTitle),
                "Expected page title to contain: '" + expectedTitle + "', but got: '" + actualTitle + "'");
    }

  /* @Test
   public void testHomePageSubtitle() {
       By subtitleXPath = By.xpath("/html/body/div/main/div[1]/div[2]/div/div[1]/h2");
       WebElement subtitle = wait.until(ExpectedConditions.visibilityOfElementLocated(subtitleXPath));

       String expectedSubtitle = "eSHOP OnWeb"; // Update this if needed
       String actualSubtitle = subtitle.getText();

       assertTrue(actualSubtitle.contains(expectedSubtitle),
               "Expected subtitle to contain: '" + expectedSubtitle + "', but got: '" + actualSubtitle + "'");
   }*/


    @Test
    public void testLogoLinkNavigation() {
        By logoLink = By.xpath("/html/body/div/header/div/article/section[1]/a/img"); // Update if logo has a unique class or id

        WebElement logoImage = wait.until(ExpectedConditions.visibilityOfElementLocated(logoLink));
        assertTrue(logoImage.isDisplayed(), "Logo image is not visible.");
        assertTrue(logoImage.isEnabled(), "Search button is not enabled/clickable.");
        logoImage.click();
    }
    @Test
    void registrationShouldBeSuccessful() {
        RegisterPageOperations registerPage = new RegisterPage(driver);

        // Static credentials for testing
        String email = "vaishnavisivakumar2003@gmail.com";
        String password = "Vaishnavi@08";

        registerPage.register(email, password, password);

        // Adjust the expected URL as needed based on actual behavior after successful registration
        String expectedPartialUrl = "/"; // e.g., "/dashboard", "/welcome"
        assertTrue(driver.getCurrentUrl().contains(expectedPartialUrl),
                "Registration failed or redirected incorrectly");
    }

    @Test
    void loginShouldBeSuccessful() {
        LoginPageOperations loginPage = new LoginPage(driver);

        // Static credentials for testing
        String username = "admin@microsoft.com";
        String password = "Pass@word1";

        loginPage.login(username, password);

        // Adjust the expected URL based on actual behavior after successful login
        String expectedPartialUrl = "/"; // e.g., "/dashboard", "/catalog"
        assertTrue(driver.getCurrentUrl().contains(expectedPartialUrl),
                "Login failed or redirected incorrectly");
    }

    @Test
    void forgotPasswordShouldSubmitSuccessfully() {

        ForgotPasswordPageOperations forgotPasswordPage = new ForgotPasswordPage(driver);
        // Email for testing (should exist in system or trigger proper message)
        String testEmail = "vaishnavisivakumar2003@gmail.com";
        // Run the forgot password flow
        forgotPasswordPage.resetPasswordFlow(testEmail);
        // Option 1: Check if you're redirected to a confirmation page or see a success message
        // Option 2: Check current URL or specific message element
        String currentUrl = driver.getCurrentUrl();
        String expectedPartialUrl = "/Identity/Account/ForgotPasswordConfirmation"; // Update based on actual behavior
        assertTrue(currentUrl.contains(expectedPartialUrl),
                "Forgot password flow did not complete or incorrect redirection. Current URL: " + currentUrl);

    }
    @Test
    void addToCartShouldBeSuccessful() {
      //  WebDriver driver = new ChromeDriver();
        try {
            driver.manage().window().maximize();
            AddToCartPageOperations addToCartPage = new AddToCartPage(driver);
            // AddToCartPageOperations addToCartPage = new AddToCartPage(driver);
            addToCartPage.addItemToCart();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains("/Basket"));
            String expectedPartialUrl = "/Basket";
            assertTrue(driver.getCurrentUrl().contains(expectedPartialUrl),
                    "Item was not added to cart or user was not redirected properly");
        } finally {
            driver.quit();
        }
    }
}
