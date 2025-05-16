package testpackage;
import static org.junit.jupiter.api.Assertions.assertEquals;

<<<<<<< HEAD
import org.junit.jupiter.api.Test;

=======
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


>>>>>>> d25743bf309d5049166cac980ff42460cdbc23ad
class FirstTest {

    //private final Calculator calculator = new Calculator();

    @Test
    void addition() {
        assertEquals(2, 1+1);
    }
    @Test
    void fail_this() {
        assertEquals(2, 1+1);
    }
<<<<<<< HEAD
=======

    @Test
    void OtherThanTest(){}
>>>>>>> d25743bf309d5049166cac980ff42460cdbc23ad
}
