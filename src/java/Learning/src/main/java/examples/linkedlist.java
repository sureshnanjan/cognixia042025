package examples;
import java.util.*;

public class linkedlist {
    // import java.util.*;

    // public class LinkedListCRUD {
    private LinkedList<String> list = new LinkedList<>();

    // CREATE - Add item to the end
    public void create(String item) {
        list.add(item);
        System.out.println("Added: " + item);
    }

    // READ - Display all items
    public void readAll() {
        if (list.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            System.out.println("Current List: " + list);
        }
    }

    // READ - Get item at specific index
    public void read(int index) {
        if (index >= 0 && index < list.size()) {
            System.out.println("Item at index " + index + ": " + list.get(index));
        } else {
            System.out.println("Invalid index.");
        }
    }

    // UPDATE - Modify item at a specific index
    public void update(int index, String newItem) {
        if (index >= 0 && index < list.size()) {
            String oldItem = list.set(index, newItem);
            System.out.println("Updated: '" + oldItem + "' to '" + newItem + "'");
        } else {
            System.out.println("Invalid index.");
        }
    }

    // DELETE - Remove item by value
    public void delete(String item) {
        if (list.remove(item)) {
            System.out.println("Removed item: " + item);
        } else {
            System.out.println("Item not found: " + item);
        }
    }

    // DELETE - Remove item by index
    public void delete(int index) {
        if (index >= 0 && index < list.size()) {
            String removed = list.remove(index);
            System.out.println("Removed item at index " + index + ": " + removed);
        } else {
            System.out.println("Invalid index.");
        }
    }

    // MAIN METHOD for testing
    public static void main(String[] args) {
        linkedlist crud = new linkedlist();

        // CREATE
        crud.create("Apple");
        crud.create("Banana");
        crud.create("Cherry");

        // READ ALL
        crud.readAll();

        // READ by index
        crud.read(1);

        // UPDATE
        crud.update(1, "Blueberry");
        crud.readAll();

        // DELETE by value
        crud.delete("Cherry");
        crud.readAll();

        // DELETE by index
        crud.delete(0);
        crud.readAll();

        // Try deleting an invalid index
        crud.delete(10);
    }
}



