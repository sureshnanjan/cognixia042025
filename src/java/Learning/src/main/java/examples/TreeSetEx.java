package examples;

import java.util.*;

public class TreeSetEx {
    TreeSet<String> treeSet = new TreeSet<>();

    public void create(String item) {
        treeSet.add(item);
    }

    public void read() {
        System.out.println("TreeSet (Sorted): " + treeSet);
    }

    public void update(String oldItem, String newItem) {
        if (treeSet.contains(oldItem)) {
            treeSet.remove(oldItem);
            treeSet.add(newItem);
        } else {
            System.out.println("Item not found in TreeSet.");
        }
    }

    public void delete(String item) {
        treeSet.remove(item);
    }
}
