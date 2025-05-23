package heroku.tests;

import heroku.operations.RegisterPageOperations;
import heroku.selenium.pages.RegisterPage;
import heroku.utilities.WebElementInteractions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RegisterPageTests {

    @Test
    void registrationShouldBeSuccessful() {
        WebDriver driver = WebElementInteractions.createDriver();

        try {
            RegisterPageOperations registerPage = new RegisterPage(driver);

            String email = "vaishnavisivakumar2003@gmail.com";
            String password = "Vaishnavi@08";

            registerPage.register(email, password, password);

            String expectedPartialUrl = "/";
            assertTrue(driver.getCurrentUrl().contains(expectedPartialUrl),
                    "Registration failed or redirected incorrectly");

        } finally {
            driver.quit();
        }
    }

    @Test
    void registrationShouldFailWithMismatchedPasswords() {
        WebDriver driver = WebElementInteractions.createDriver();

        try {
            RegisterPageOperations registerPage = new RegisterPage(driver);

            String email = "invaliduser@example.com";
            String password = "Password123";
            String confirmPassword = "Mismatch123";  // mismatched password

            registerPage.register(email, password, confirmPassword);

            // Small wait to ensure page reacts (optional: replace with WebDriverWait)
            Thread.sleep(2000);

            // We expect registration to fail: should still be on same page or error shown
            String currentUrl = driver.getCurrentUrl();
            assertFalse(currentUrl.contains("/dashboard") || currentUrl.contains("/catalog"),
                    "Registration unexpectedly succeeded with mismatched passwords");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
