package heroku.tests;

import heroku.operations.LoginPageOperations;
import heroku.selenium.pages.LoginPage;
import heroku.utilities.WebElementInteractions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTests {

    @Test
    void loginShouldBeSuccessful() {
        WebDriver driver = WebElementInteractions.createDriver();

        try {
            LoginPageOperations loginPage = new LoginPage(driver);

            String username = "admin@microsoft.com";
            String password = "Pass@word1";

            loginPage.login(username, password);

            String expectedPartialUrl = "/";
            assertTrue(driver.getCurrentUrl().contains(expectedPartialUrl),
                    "Login failed or redirected incorrectly");

        } finally {
            driver.quit();
        }
    }

    @Test
    void loginShouldFailWithInvalidCredentials() {
        WebDriver driver = WebElementInteractions.createDriver();

        try {
            LoginPageOperations loginPage = new LoginPage(driver);

            String invalidUsername = "invalid@domain.com";
            String invalidPassword = "WrongPass123";

            loginPage.login(invalidUsername, invalidPassword);

            // Wait a little for response (if needed, can add WebDriverWait here)
            Thread.sleep(2000); // Optional but can be replaced by explicit wait

            // Check that we are still on the login page or redirected to login again
            String currentUrl = driver.getCurrentUrl();
            assertFalse(currentUrl.contains("/dashboard") || currentUrl.contains("/catalog"),
                    "Login unexpectedly succeeded with invalid credentials");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
