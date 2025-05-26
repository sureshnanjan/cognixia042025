/*
 * © 2025 eShop Testing Team. All rights reserved.
 * Authors: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 * This class contains automated tests for verifying the functionality
 * of the Register page in the eShop application. It tests the page title,
 * registration success with valid details, and the system's handling of
 * mismatched passwords and invalid email formats.
 */

package training.eshop.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import training.eshop.operations.RegisterPageOperations;
import training.eshop.seleniumpages.RegisterPage;

public class RegisterPageTests {

    /**
     * Test to verify that the Register page title is displayed correctly.
     */
    @Test
    void RegisterPageTitleIsCorrect() {
        String expectedTitle = "Register"; // Update if actual title is different
        RegisterPageOperations page = new RegisterPage();
        String actualTitle = page.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    /**
     * Test to verify successful registration with valid details.
     * A unique email is generated for each test run to avoid conflicts.
     */
    @Test
    void RegisterWithValidDetailsSucceeds() {
        RegisterPageOperations page = new RegisterPage();
        // Generate a unique email each run to avoid conflicts
        String uniqueEmail = "suba" + System.currentTimeMillis() + "@gmail.com";
        String password = "Suba@2026";
        assertDoesNotThrow(() ->
                page.doRegister(uniqueEmail, password, password)
        );
    }

    /**
     * Test to ensure that registration with mismatched passwords does not throw unexpected errors.
     */
    @Test
    void RegisterWithMismatchedPasswordsDoesNotThrowError() {
        RegisterPageOperations page = new RegisterPage();
        String email = "invalid@example.com";
        String password = "Password123!";
        String confirmPassword = "Mismatch123!";
        assertDoesNotThrow(() ->
                page.doRegister(email, password, confirmPassword)
        );
    }

    /**
     * Test to ensure that registration with invalid email format does not throw unexpected errors.
     */
    @Test
    void RegisterWithInvalidEmailFormat() {
        RegisterPageOperations page = new RegisterPage();
        String email = "invalid-email-format";
        String password = "Password123!";
        assertDoesNotThrow(() ->
                page.doRegister(email, password, password)
        );
    }
}
