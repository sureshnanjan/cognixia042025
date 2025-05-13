package examples;
import java.util.HashMap;

public class HashMapDS {
    private HashMap<Integer, String> map = new HashMap<>();

    // Create
    public void create(int key, String value) {
        map.put(key, value);
    }

    // Read
    public void read() {
        System.out.println("HashMap: " + map);
    }

    // Update
    public void update(int key, String newValue) {
        if (map.containsKey(key)) {
            map.put(key, newValue);
        } else {
            System.out.println("Key not found");
        }
    }

    // Delete
    public void delete(int key) {
        if (map.containsKey(key)) {
            map.remove(key);
        } else {
            System.out.println("Key not found");
        }
    }
}
