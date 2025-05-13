package examples;
import java.util.*;

public class MapCRUD {

    private Map<String, String> map = new HashMap<>();

    // CREATE - Add new key-value pair
    public void create(String key, String value) {
        if (map.containsKey(key)) {
            System.out.println("Key already exists. Use update() to modify it.");
        } else {
            map.put(key, value);
            System.out.println("Added: {" + key + ": " + value + "}");
        }
    }

    // READ - View all entries
    public void readAll() {
        if (map.isEmpty()) {
            System.out.println("Map is empty.");
        } else {
            System.out.println("Current Map: " + map);
        }
    }

    // READ - Get value by key
    public void read(String key) {
        if (map.containsKey(key)) {
            System.out.println("Value at key '" + key + "': " + map.get(key));
        } else {
            System.out.println("Key not found: " + key);
        }
    }

    // UPDATE - Modify value of an existing key
    public void update(String key, String newValue) {
        if (map.containsKey(key)) {
            String oldValue = map.put(key, newValue);
            System.out.println("Updated: {" + key + ": " + oldValue + "} → {" + key + ": " + newValue + "}");
        } else {
            System.out.println("Key not found: " + key);
        }
    }

    // DELETE - Remove entry by key
    public void delete(String key) {
        if (map.containsKey(key)) {
            String removedValue = map.remove(key);
            System.out.println("Removed: {" + key + ": " + removedValue + "}");
        } else {
            System.out.println("Key not found: " + key);
        }
    }

    // MAIN METHOD for testing
    public static void main(String[] args) {
        MapCRUD crud = new MapCRUD();

        // CREATE
        crud.create("user1", "Alice");
        crud.create("user2", "Bob");
        crud.create("user3", "Charlie");

        // READ ALL
        crud.readAll();

        // READ by key
        crud.read("user2");

        // UPDATE
        crud.update("user2", "Bob Updated");
        crud.readAll();

        // DELETE
        crud.delete("user1");
        crud.readAll();

        // DELETE non-existent key
        crud.delete("userX");
    }
}
