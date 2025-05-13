package examples;
import java.util.TreeMap;

public class TreeMapDS {
    public TreeMap<Integer, String> treeMap = new TreeMap<>();

    // Create
    public void create(int key, String value) {
        treeMap.put(key, value);
    }

    // Read
    public void read() {
        System.out.println("TreeMap (sorted): " + treeMap);
    }

    // Update
    public void update(int key, String newValue) {
        if (treeMap.containsKey(key)) {
            treeMap.put(key, newValue);
        } else {
            System.out.println("Key not found");
        }
    }

    // Delete
    public void delete(int key) {
        if (treeMap.containsKey(key)) {
            treeMap.remove(key);
        } else {
            System.out.println("Key not found");
        }
    }
}

