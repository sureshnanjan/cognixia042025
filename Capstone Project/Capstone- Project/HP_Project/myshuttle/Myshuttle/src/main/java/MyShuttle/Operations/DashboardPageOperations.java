package MyShuttle.Operations;

import java.util.List;

public interface DashboardPageOperations {
    String getDashboardHeading();

    boolean isLogoVisible();

    void clickFareHistoryLink();

    void logout();

    String getCurrentUrl();

//    void goBackToPreviousPage();
}
