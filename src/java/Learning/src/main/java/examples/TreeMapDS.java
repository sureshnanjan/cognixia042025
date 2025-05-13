package examples;

import java.util.TreeMap;
public class TreeMapDS {
    private TreeMap<Integer, String> treeMap = new TreeMap<>();

    public void create(int key, String value) {
        treeMap.put(key, value);
}

public void read() {
        System.out.println("TreeMap (sorted): " + treeMap);
    }

           public void update(int key, String newValue) {
            if (treeMap.containsKey(key)) {
        treeMap.put(key, newValue);
        } else {
                System.out.println("Key not found");
        }
                }

                public void delete(int key) {
                if (treeMap.containsKey(key)) {
        treeMap.remove(key);
        } else {
                System.out.println("Key not found");
        }
                }
                }