package examples;

import java.util.LinkedList;
public class LinkedListDS {
    private LinkedList<String> list = new LinkedList<>();

    public void create(String value) {
        list.add(value);
    }

    public void read() {
        System.out.println("LinkedList: " + list);
    }

    public void update(int index, String newValue) {
        if (index >= 0 && index < list.size()) {
            list.set(index, newValue);
        } else {
            System.out.println("Invalid index");
        }
    }

    public void delete(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        } else {
            System.out.println("Invalid index");
        }
    }
}
