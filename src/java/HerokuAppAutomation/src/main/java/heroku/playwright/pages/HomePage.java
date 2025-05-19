package heroku.playwright.pages;

import heroku.operations.ContextClickOperations;
import heroku.operations.HomePageOperations;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage implements HomePageOperations {
    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSubTitle() {
        return null;
    }

    @Override
    public List<WebElement> getAllExamples() {
        return null;
    }

    @Override
    public String getExampleName(int position) {
        return null;
    }

    @Override
    public Object goToExample(String exampleName) {
        return  null;

    }
}
