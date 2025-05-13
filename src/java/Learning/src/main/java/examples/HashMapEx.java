package examples;
import java.util.*;

public class HashMapEx {
    Map<String, String> map = new HashMap<>();

    public void create(String key, String value) {
        map.put(key, value);
    }

    public void read() {
        System.out.println("Map: " + map);
    }

    public void update(String key, String newValue) {
        if (map.containsKey(key)) {
            map.put(key, newValue);
        } else {
            System.out.println("Key not found.");
        }
    }

    public void delete(String key) {
        map.remove(key);
    }
}
