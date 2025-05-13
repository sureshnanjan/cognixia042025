package examples;

import java.util.HashSet;
public class HashSetDS {
    private HashSet<String> set = new HashSet<>();

 public void create(String value) {
        if (set.add(value)) {
        System.out.println("Added: " + value);
    } else {
        System.out.println("Value already exists");
    }
}

public void read() {
        System.out.println("HashSet: " + set);
    }

            public void update(String oldValue, String newValue) {
            if (set.remove(oldValue)) {
        set.add(newValue);
        } else {
                System.out.println("Old value not found");
        }
                }

                public void delete(String value) {
                if (set.remove(value)) {
        System.out.println("Deleted: " + value);
        } else {
                System.out.println("Value not found");
        }
                }
                }
