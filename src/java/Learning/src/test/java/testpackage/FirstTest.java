package testpackage;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


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

    @Test
    void OtherThanTest(){}
}
