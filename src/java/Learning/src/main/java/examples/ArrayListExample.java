package examples;
import java.util.*;

public class ArrayListExample {

    private List<String> list = new ArrayList<>();

    // Create
    public void create(String item) {
        list.add(item);
        System.out.println("Item added: " + item);
    }

    // Read
    public void read() {
        System.out.println("List: " + list);
    }

    // Update
    public void update(int index, String newItem) {
        if (index >= 0 && index < list.size()) {
            String oldItem = list.set(index, newItem);
            System.out.println("Updated item '" + oldItem + "' to '" + newItem + "'");
        } else {
            System.out.println("Invalid index.");
        }
    }

    // Delete
    public void delete(String item) {
        if (list.remove(item)) {
            System.out.println("Item deleted: " + item);
        } else {
            System.out.println("Item not found: " + item);
        }
    }

    public static void main(String[] args) {
        ArrayListExample ex = new ArrayListExample();

        ex.create("Apple");
        ex.create("Banana");
        ex.read();

        ex.update(1, "Blueberry");
        ex.read();

        ex.delete("Apple");
        ex.read();
    }
}

