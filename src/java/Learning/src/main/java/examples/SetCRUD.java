package examples;
import java.util.*;

public class SetCRUD {

    private Set<String> set = new HashSet<>();

    // CREATE - Add an item
    public void create(String item) {
        if (set.add(item)) {
            System.out.println("Added: " + item);
        } else {
            System.out.println("Item already exists: " + item);
        }
    }

    // READ - Display all elements
    public void readAll() {
        if (set.isEmpty()) {
            System.out.println("Set is empty.");
        } else {
            System.out.println("Current Set: " + set);
        }
    }

    // READ - Check if item exists
    public void read(String item) {
        if (set.contains(item)) {
            System.out.println("Item found: " + item);
        } else {
            System.out.println("Item not found: " + item);
        }
    }

    // UPDATE - Replace item (remove old, add new)
    public void update(String oldItem, String newItem) {
        if (set.contains(oldItem)) {
            set.remove(oldItem);
            set.add(newItem);
            System.out.println("Updated: '" + oldItem + "' to '" + newItem + "'");
        } else {
            System.out.println("Item not found: " + oldItem);
        }
    }

    // DELETE - Remove an item
    public void delete(String item) {
        if (set.remove(item)) {
            System.out.println("Deleted: " + item);
        } else {
            System.out.println("Item not found: " + item);
        }
    }

    // MAIN method for testing
    public static void main(String[] args) {
        SetCRUD crud = new SetCRUD();

        // CREATE
        crud.create("Java");
        crud.create("Python");
        crud.create("C++");

        // READ ALL
        crud.readAll();

        // READ specific
        crud.read("Python");
        crud.read("Go");

        // UPDATE
        crud.update("Python", "Rust");
        crud.readAll();

        // DELETE
        crud.delete("C++");
        crud.readAll();

        // DELETE non-existent
        crud.delete("Go");
    }
}
