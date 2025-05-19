package testpackage;

import examples.BinarySearcher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;



public class BinarySercherTests {
    /*
    * Parameters:
a - the array to be searched
fromIndex - the index of the first element (inclusive) to be searched
toIndex - the index of the last element (exclusive) to be searched
key - the value to be searched for
c - the comparator by which the array is ordered. A null value indicates that the elements' natural ordering should be used.
Returns:
index of the search key,
* if it is contained in the array within the specified range;
* otherwise,
* (-(insertion point) -
* 1). The insertion point is defined as the point at which the key would be
* inserted into the array: the index of the first element in the range greater than the key,
* or toIndex if all elements in the range are less than the specified key.
* Note that this guarantees that the return value will be >= 0 if and only if the key is found.
Throws:
ClassCastException -
* if the range contains elements that are not mutually comparable
* using the specified comparator, or the search key is not comparable to the
* elements in the range using this comparator.
IllegalArgumentException - if fromIndex > toIndex
ArrayIndexOutOfBoundsException - if fromIndex < 0 or toIndex > a.length
    * */
    @Test
    void testforElementExistsinBeginning(){
        BinarySearcher sut = new BinarySearcher();
        int[] inputs = {1,2,3,4,5}; // BVC
        int key = 4;
        int expected = 3;
        // Act
        int actual = sut.doSearch(inputs, key);
        // Assert
        assertEquals(expected, actual, "The result did not match");


    }
    @Test
    void testforElementExistsinMiddle(){
        BinarySearcher sut = new BinarySearcher();
        int[] inputs = {1,2,3,4,5}; // BVC
        int key = 4;
        int expected = 3;
        // Act
        int actual = sut.doSearch(inputs, key);
        // Assert
        assertEquals(expected, actual, "The result did not match");


    }
    @Test
    void testforElementExistsatEnd(){
        BinarySearcher sut = new BinarySearcher();
        int[] inputs = {1,2,3,4,5}; // BVC
        int key = 5;
        int expected = 4;
        // Act
        int actual = sut.doSearch(inputs, key);
        // Assert
        assertEquals(expected, actual, "The result did not match");


    }

    @Test
    void testforElementNotExistsHighest(){
        /// AAA
        BinarySearcher sut = new BinarySearcher();
        int[] inputs = {1,2,4,5,6};
        int key = 10;
        int expected = -1;
        // Act
        int actual = sut.doSearch(inputs, key);
        // Assert
        assertEquals(expected, actual, "The result did not match");

    }

    @Test
    void testforElementNotExistsandLowest(){
        /// AAA
        BinarySearcher sut = new BinarySearcher();
        int[] inputs = {1,2,4,5,6};
        int key = 0;
        int expected = -1;
        // Act
        int actual = sut.doSearch(inputs, key);
        // Assert
        assertEquals(expected, actual, "The result did not match");

    }

    @Test
    void testforElementNotExistsandBetween(){
        /// AAA
        BinarySearcher sut = new BinarySearcher();
        int[] inputs = {1,2,4,5,6,10};
        int key = 7;
        int expected = -1;
        // Act
        int actual = sut.doSearch(inputs, key);
        // Assert
        assertEquals(expected, actual, "The result did not match");

    }

    @Test()
    void testClassExceptionThrownCorrectly(){
        BinarySearcher sut = new BinarySearcher();
        int[] inputs = {1,2,4,5,6,10};
        float key = 7.0f;
        int expected = -1;
        // Act
        assertThrowsExactly(ClassCastException.class, () ->{
            int actual = sut.doSearch(inputs,key);
        });

    }

}
