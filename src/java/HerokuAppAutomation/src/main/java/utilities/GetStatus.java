package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * Utility class to check the status of a web element (displayed, enabled, selected).
 */
public class GetStatus{

    /**
     * Checks and returns the status of a web element (displayed, enabled, selected).
     *
     * @param element The WebElement instance to check.
     * @return A string summarizing the element's status.
     */
    public static String getStatusUtil(WebElement element) {
        try {
            boolean isDisplayed = element.isDisplayed();
            boolean isEnabled = element.isEnabled();
            boolean isSelected = element.isSelected();

            String status = String.format("Status - Displayed: %s, Enabled: %s, Selected: %s",
                    isDisplayed, isEnabled, isSelected);

            System.out.println(status);
            return status;

        } catch (NoSuchElementException e) {
            String msg = "Element not found for status check.";
            System.out.println(msg);
            return msg;
        } catch (StaleElementReferenceException e) {
            String msg = "Element is stale; status cannot be determined.";
            System.out.println(msg);
            return msg;
        } catch (Exception e) {
            String msg = "Error checking element status: " + e.getMessage();
            System.out.println(msg);
            return msg;
        }
    }
}
