package MyShuttle.Operations;

import java.util.List;



/**
 * Interface defining operations that can be performed on the Dashboard page.
 */
public interface DashboardPageOperations {




    /**
     * Retrieves the heading text displayed on the dashboard.
     * @return the dashboard heading
     */
    String getDashboardHeading();





    /**
     * Checks whether the logo is visible on the dashboard.
     * @return true if the logo is visible, false otherwise
     */
    boolean isLogoVisible();





    /**
     * Clicks the "Fare History" link on the dashboard.
     */
    void clickFareHistoryLink();




    /**
     * Logs the user out from the application.
     */
    void logout();




    /**
     * Retrieves the current page URL.
     * @return the current URL
     */
    String getCurrentUrl();

//    void goBackToPreviousPage();
}
