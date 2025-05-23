package heroku.operations;

/**
 * Interface for handling checkout functionality.
 */
public interface CheckoutPageOperations {
    /**
     * Clicks the checkout button to proceed to the payment section.
     */
    void clickCheckoutButton();

    /**
     * Clicks the pay now button to complete the purchase.
     */
    void clickPayNowButton();

    /**
     * Executes the complete checkout process:
     * click checkout → click pay now.
     */
    void completePurchase();

    /**
     * Verifies if the success message after purchase is displayed.
     * @return true if purchase was successful, false otherwise
     */
    boolean isPurchaseSuccessful();
}
