/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  JUnit 5 test class for Account Details page operations in the eShop project.
 *  Contains unit tests to verify page title, profile updates, password change, and expected
 *  exceptions for unimplemented features.
 * =======================================================================================
 */

package training.eshop.tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import training.eshop.operations.AccountDetailsOperations;
import training.eshop.seleniumpages.AccountDetailsPage;

public class AccountDetailsTests {

    private AccountDetailsOperations page;

    /**
     * Setup before each test.
     * Initializes the AccountDetailsPage instance.
     */
    @BeforeEach
    void setup() {
        page = new AccountDetailsPage();
    }

    /**
     * Cleanup after each test.
     * Closes the browser window if the page instance is AccountDetailsPage.
     */
    @AfterEach
    void teardown() {
        if (page instanceof AccountDetailsPage) {
            ((AccountDetailsPage) page).closeBrowser();
        }
    }

    /**
     * Test to verify the page title is as expected.
     */
    @Test
    void AccountPageTitleIsCorrect() {
        String expected = "Manage your account";
        String actual = page.getTitle();
        assertEquals(expected, actual);
    }

    /**
     * Test to verify changing the profile updates the phone number correctly.
     */
    @Test
    void ChangeProfileUpdatesPhoneNumber() {
        String testPhone = "1234567890";
        page.ChangeProfile(testPhone);

        String actualPhone = page.getPhoneNumber();
        assertEquals(testPhone, actualPhone);
    }

    /**
     * Test to verify that changing the password with valid data does not throw exceptions.
     */

    @Test
    void ChangePasswordDisplaysSuccessMessage() {
        String expected = "×\nYour password has been changed.";
        String actual = page.changePasswordAndReturnMessage("Suba@2025", "Suba@2002", "Suba@2002");
        assertEquals(expected, actual);
    }


    /**
     * Test to verify that sending a verification email throws the expected UnsupportedOperationException.
     */
    @Test
    void SendVerificationEmailFailsAsExpected() {
        assertThrows(UnsupportedOperationException.class, () ->
                page.SendVerificationEmail()
        );
    }

    /**
     * Test to verify that enabling two-factor authentication throws the expected UnsupportedOperationException.
     */
    @Test
    void TwoFactorAuthenticationFailsAsExpected() {
        assertThrows(UnsupportedOperationException.class, () ->
                page.TwoFactorAuthentication()
        );
    }
}
