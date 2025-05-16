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
        // AAA - Arrange, Act, Assert

        // Arrange
        String expected = "Available Examples";
        HomePageOperations hpo = new HomePage();

        // Act
        String actual = hpo.getSubTitle();

        // Assert
        assertEquals(expected, actual, "Title on Home Page is incorrect");

    }
    @Test
    public void  availableExampleCountis44(){
        // Arrange
        int expected = 44;
        HomePageOperations hpo = new HomePage();

        // Act
        int actual = hpo.getAllExamples().size();

        // Assert
        assertEquals(expected, actual, "Not all examples are available");

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
