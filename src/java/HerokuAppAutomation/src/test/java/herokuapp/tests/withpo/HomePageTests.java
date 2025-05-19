package herokuapp.tests.withpo;

import heroku.operations.HomePageOperations;
import heroku.selenium.pages.HomePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class HomePageTests {
    @Test
    void  checkTitleOK(){
        ///AAA
        String expected = "Welcome to the-internet";
        HomePageOperations hpo = new HomePage();
        String actual = hpo.getTitle();
        assertEquals(expected, actual, "Title on Home Page is incorrect");

    }

    @Test
    void  checkSubTitleOK(){
        ///AAA
        String expected = "Welcome to the Internet";
        HomePageOperations hpo;
        //String actual = hpo.getSubTitle();
        //assertEquals(expected, actual, "Title on Home Page is incorrect");

    }
    @Test
    void  availableExampleCountis44(){
        ///AAA
        int expected = 44;
        HomePageOperations hpo;
        //int actual = hpo.getAllExamples().size();
        //assertEquals(expected, actual, "Not all examples are available");

    }

    @Test
    void  firstExampleIsABTest(){
        ///AAA
        String  expected = "A/B Testing";
        HomePageOperations hpo;
        //String actual = hpo.getExampleName(1);
        //assertEquals(expected, actual, "First Example should be A/B Testing");

    }
}
