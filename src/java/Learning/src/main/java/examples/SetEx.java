package examples;

import java.util.*;

public class SetEx {
    Set<String> set = new HashSet<>();

    public void create(String item) {
        set.add(item);
    }

    public void read() {
        System.out.println("Set: " + set);
    }

    public void update(String oldItem, String newItem) {
        if (set.contains(oldItem)) {
            set.remove(oldItem);
            set.add(newItem);
        } else {
            System.out.println("Item not found in set.");
        }
    }

    public void delete(String item) {
        set.remove(item);
    }
}
