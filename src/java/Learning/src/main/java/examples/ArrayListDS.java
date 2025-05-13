package examples;
import java.util.ArrayList;

public class ArrayListDS {
    private ArrayList<Integer> list = new ArrayList<>();

    // Create
    public void create(int value) {
        list.add(value);
    }

    // Read
    public void read() {
        System.out.println("ArrayList: " + list);
    }

    // Update
    public void update(int index, int newValue) {
        if (index >= 0 && index < list.size()) {
            list.set(index, newValue);
        } else {
            System.out.println("Index out of bounds");
        }
    }

    // Delete
    public void delete(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        } else {
            System.out.println("Index out of bounds");
        }
    }
}
