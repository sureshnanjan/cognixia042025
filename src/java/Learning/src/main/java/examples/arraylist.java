package examples;

import java.util.ArrayList;
import java.util.List;

public class arraylist {
    // import java.util.*;

    //public class ArrayListEx {
    private List<String> list = new ArrayList<>();

    public void create(String item) {
        list.add(item);
        System.out.println("Item added: " + item);
    }

    public void read() {
        System.out.println("List: " + list);
    }

    public void update(int index, String newItem) {
        if (index >= 0 && index < list.size()) {
            String oldItem = list.set(index, newItem);
            System.out.println("Updated item '" + oldItem + "' to '" + newItem + "'");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void delete(String item) {
        if (list.remove(item)) {
            System.out.println("Item deleted: " + item);
        } else {
            System.out.println("Item not found: " + item);
        }
    }

    public static void main(String[] args) {
        arraylist ex = new arraylist();
        ex.create("Apple");
        ex.create("Banana");
        ex.read();
        ex.update(1, "Blueberry");
        ex.read();
        ex.delete("Apple");
        ex.read();
    }
}



