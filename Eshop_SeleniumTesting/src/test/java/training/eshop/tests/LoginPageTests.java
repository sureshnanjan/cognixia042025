/*
 * © 2025 eShop Testing Team. All rights reserved.
 * Author: Subalakshmi
 *
 * This class contains test cases for verifying the login and logout
 * functionalities of the eShop application login page.
 */

package training.eshop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import training.eshop.seleniumpages.LoginPage;

public class LoginPageTests {

    /**
     * Verifies that login is successful using valid user credentials.
     *
     * Steps:
     * 1. Create a new LoginPage instance.
     * 2. Perform login with valid email and password.
     * 3. Retrieve the username of the logged-in user.
     * 4. Assert that the retrieved username matches the expected username.
     */
    @Test
    void checkIfLoginSuccess() {
        String expectedUsername = "athesh@gmail.com";
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("athesh@gmail.com", "Athesh@2026", false);
        String actualUsername = loginPage.getLoggedInUsername();
        Assertions.assertEquals(expectedUsername, actualUsername);
    }

    /**
     * Verifies that logout functionality works correctly.
     *
     * Steps:
     * 1. Create a new LoginPage instance.
     * 2. Login with valid credentials.
     * 3. Perform logout using the same credentials.
     * 4. Assert that the login button is visible, indicating a successful logout.
     */
    @Test
    void checkLogout() {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Athesh@gmail.com", "Athesh@2026", false);
        loginPage.doLogout("Athesh@gmail.com", "Athesh@2026", false);
        Assertions.assertTrue(loginPage.isLoginButtonVisible(), "Logout failed - Login button not visible");
    }
}
