package MyShuttle.Operations;

import java.util.List;



/**
 * Interface defining operations for the Fare History page.
 */
public interface FareHistoryPageOperations {




    /**
     * Retrieves the heading displayed on the Fare History page.
     * @return the fare history heading
     */
    String getFareHistoryHeading();





    /**
     * Retrieves the list of table header titles.
     * @return a list of table header names
     */
    List<String> getTableHeaders();




    /**
     * Retrieves the data for a specific row in the fare history table.
     * @param rowNumber the row number to retrieve (0-based index)
     * @return a list of cell values for the specified row
     */
    List<String> getTableRowData(int rowNumber);





    /**
     * Navigates back to the previous page.
     */
    void goBack();
}
