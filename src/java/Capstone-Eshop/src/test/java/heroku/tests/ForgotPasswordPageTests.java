package heroku.tests;
import heroku.operations.ForgotPasswordPageOperations;
import heroku.selenium.pages.ForgotPasswordPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForgotPasswordPageTests {

    @Test
    void forgotPasswordShouldSubmitSuccessfully() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

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

        driver.quit();
    }
}

