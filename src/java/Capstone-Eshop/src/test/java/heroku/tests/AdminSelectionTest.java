//package heroku.tests;
//
//import heroku.operations.LoginPageOperations;
//import heroku.operations.HomePageOperations;
//import heroku.utilities.WebElementInteractions;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebDriver;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class AdminSelectionTest {
//
//    @Test
//    void loginAndSelectAdmin() {
//        WebDriver driver = WebElementInteractions.createDriver();
//
//        try {
//            // Login
//            LoginPageOperations loginPage = new LoginPageOperations(driver);
//            loginPage.login("admin@microsoft.com", "Pass@word1");
//
//            // Select Admin from dropdown
//            HomePageOperations homePageOps = new HomePageOperations(driver);
//            homePageOps.goToAdmin();
//
//            // Add assertion here if you want to verify you're on the Admin page, e.g.:
//            String currentUrl = driver.getCurrentUrl();
//            assertTrue(currentUrl.contains("/Admin"), "Did not navigate to Admin page");
//
//        } finally {
//            driver.quit();
//        }
//    }
//}
