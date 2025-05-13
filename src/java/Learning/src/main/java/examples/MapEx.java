package examples;

import java.util.*;

public class MapEx {
    Map<Integer, String> map = new HashMap<>();

    public void create(int key, String value) {
        map.put(key, value);
    }

    public void read() {
        System.out.println("Map: " + map);
    }

    public void update(int key, String newValue) {
        if (map.containsKey(key)) {
            map.put(key, newValue);
        } else {
            System.out.println("Key not found in map.");
        }
    }

    public void delete(int key) {
        map.remove(key);
    }
}
