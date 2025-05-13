package examples;

import java.util.HashMap;
public class HashMapDS {
    private HashMap<Integer, String> map = new HashMap<>();

    public void create(int key, String value) {
        map.put(key, value);
}

public void read() {
        System.out.println("HashMap: " + map);
    }

           public void update(int key, String newValue) {
            if (map.containsKey(key)) {
        map.put(key, newValue);
        } else {
                System.out.println("Key not found");
        }
                }

                public void delete(int key) {
                if (map.containsKey(key)) {
        map.remove(key);
        } else {
                System.out.println("Key not found");
        }
                }
                }
