package herokuapp.tests.withpo;

import heroku.operations.ContextClickOperations;
import heroku.selenium.pages.HomePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContextClickTests {
    @Test
    void CheckTitle(){}

    @Test
    void checkContextClickWorks(){
        HomePage page = new HomePage();
        ContextClickOperations ctxpage = (ContextClickOperations) page.goToExample("Context Menu");
        //ContextClickOperations ctpage;
        String expected = "You selected a context menu1";
        ctxpage.doHotSpotInvoke();
        String actual_result = ctxpage.getResultText();
        assertEquals(expected, actual_result,"The Result was not correct");
    }
}
