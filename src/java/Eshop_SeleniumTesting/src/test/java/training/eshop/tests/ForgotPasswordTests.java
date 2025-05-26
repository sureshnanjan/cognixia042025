/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  JUnit 5 test class for Forgot Password functionality in the eShop project.
 *  Tests include verifying the page title and successful submission of the forgot password form.
 * =======================================================================================
 */

package training.eshop.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import training.eshop.operations.ForgotPasswordOperations;
import training.eshop.seleniumpages.ForgotPasswordPage;

public class ForgotPasswordTests {

    /**
     * Test to verify the Forgot Password page title is correct.
     */
    @Test
    void ForgotPasswordPageTitleIsCorrect() {
        String expected = "Forgot your password?";
        ForgotPasswordOperations page = new ForgotPasswordPage();
        String actual = page.getTitle();
        assertEquals(expected, actual);
    }

    /**
     * Test to verify the forgot password form submits successfully without throwing exceptions.
     */
    @Test
    void ForgotPasswordFormSubmitsSuccessfully() {
        String testEmail = "test@example.com";
        ForgotPasswordOperations page = new ForgotPasswordPage();

        assertDoesNotThrow(() -> page.ChangePassword(testEmail));
    }
}
