package MyShuttle.Selenium.Pages;


import MyShuttle.Operations.FareHistoryPageOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WebElementInteractions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FareHistoryPage implements FareHistoryPageOperations {

    private WebDriver driver;

    private By heading = By.tagName("h2");
    private By tableHeaders = By.cssSelector("tr.info th");
    private String rowCssTemplate = "body > div > section > div > div.panel-body > div > table > tbody > tr:nth-child(%d)";

    public FareHistoryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public String getFareHistoryHeading() {
        WebElement headingElem = WebElementInteractions.findElementUtil(heading, driver);
        return WebElementInteractions.getTextUtil(headingElem);
    }

    @Override
    public List<String> getTableHeaders() {
        List<WebElement> headerElems = driver.findElements(tableHeaders);
        List<String> headers = new ArrayList<>();
        for (WebElement th : headerElems) {
            headers.add(th.getText().trim());
        }
        return headers;
    }

    @Override
    public List<String> getTableRowData(int rowNumber) {
        By rowLocator = By.cssSelector(String.format(rowCssTemplate, rowNumber + 1)); // +1 because header is first row, data starts at 2
        WebElement row = WebElementInteractions.findElementUtil(rowLocator, driver);
        List<String> rowData = new ArrayList<>();
        if(row != null){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                rowData.add(cell.getText().trim());
            }
        }
        return rowData;
    }

    public List<String> expectedRow1() {
        return Arrays.asList(
                "17",
                "16:00:00 2014-04-08",
                "37 Hunting Lane, Rock Gardens, WA",
                "16:24:00 2014-04-08",
                "83 Brontosaurus Blvd, Topsoil, WA",
                "$ 28.78",
                "$ 21.58",
                "1",
                "5"
        );
    }

    public List<String> expectedRow2() {
        return Arrays.asList(
                "18",
                "01:18:00 2014-05-06",
                "84 Mammoth Way, Bedrock, WA",
                "01:29:00 2014-05-06",
                "40 Stegasaurus St, Limestone, WA",
                "$ 19.1",
                "$ 14.32",
                "5",
                "1"
        );
    }

    @Override
    public void goBack() {
        driver.navigate().back();
    }
}
