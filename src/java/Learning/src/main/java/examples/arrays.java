package examples;
import java.util.*;
public class arrays {
    // import java.util.Arrays;

    // public class ArrayCRUD {
    private String[] array = new String[10]; // fixed size array
    private int size = 0; // tracks actual number of elements used

    // CREATE - Add new element
    public void create(String item) {
        if (size < array.length) {
            array[size++] = item;
            System.out.println("Added: " + item);
        } else {
            System.out.println("Array is full. Cannot add more items.");
        }
    }

    // READ - Display all elements
    public void readAll() {
        if (size == 0) {
            System.out.println("Array is empty.");
        } else {
            System.out.print("Array contents: ");
            for (int i = 0; i < size; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }

    // READ - Get element at index
    public void read(int index) {
        if (index >= 0 && index < size) {
            System.out.println("Item at index " + index + ": " + array[index]);
        } else {
            System.out.println("Invalid index.");
        }
    }

    // UPDATE - Replace element at index
    public void update(int index, String newItem) {
        if (index >= 0 && index < size) {
            String oldItem = array[index];
            array[index] = newItem;
            System.out.println("Updated: '" + oldItem + "' to '" + newItem + "'");
        } else {
            System.out.println("Invalid index.");
        }
    }

    // DELETE - Remove element by index and shift elements left
    public void delete(int index) {
        if (index >= 0 && index < size) {
            String removed = array[index];
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            array[--size] = null; // clear the last duplicate value
            System.out.println("Deleted item at index " + index + ": " + removed);
        } else {
            System.out.println("Invalid index.");
        }
    }

    // MAIN method for testing
    public static void main(String[] args) {
        arrays crud = new arrays();

        // CREATE
        crud.create("Red");
        crud.create("Green");
        crud.create("Blue");

        // READ ALL
        crud.readAll();

        // READ by index
        crud.read(1);

        // UPDATE
        crud.update(1, "Yellow");
        crud.readAll();

        // DELETE
        crud.delete(0);
        crud.readAll();

        // Attempt invalid operations
        crud.read(10);
        crud.delete(10);
    }
}


