package MyShuttle.Operations;

import java.util.List;

public interface FareHistoryPageOperations {
    String getFareHistoryHeading();

    List<String> getTableHeaders();

    List<String> getTableRowData(int rowNumber);

    void goBack();
}
