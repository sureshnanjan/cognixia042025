package examples;

public class BinarySearcher {
    public int doSearch(int[] inputs, Object key) {
        return 0;
    }
    /*
    * Parameters:
a - the array to be searched
fromIndex - the index of the first element (inclusive) to be searched
toIndex - the index of the last element (exclusive) to be searched
key - the value to be searched for
c - the comparator by which the array is ordered. A null value indicates that the elements' natural ordering should be used.
Returns:
index of the search key,
* if it is contained in the array within the specified range; otherwise,
* (-(insertion point) -
* 1). The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element in the range greater than the key, or toIndex if all elements in the range are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
Throws:
ClassCastException - if the range contains elements that are not mutually comparable using the specified comparator, or the search key is not comparable to the elements in the range using this comparator.
IllegalArgumentException - if fromIndex > toIndex
ArrayIndexOutOfBoundsException - if fromIndex < 0 or toIndex > a.length
    * */
}
