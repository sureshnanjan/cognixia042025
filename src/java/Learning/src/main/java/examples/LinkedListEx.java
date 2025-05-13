package examples;

import java.util.*;

public class LinkedListEx {
    List<String> list = new LinkedList<>();

    public void create(String item) {
        list.add(item);
    }

    public void read() {
        System.out.println("List (LinkedList): " + list);
    }

    public void update(int index, String newItem) {
        if (index >= 0 && index < list.size()) {
            list.set(index, newItem);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void delete(String item) {
        list.remove(item);
    }
}
