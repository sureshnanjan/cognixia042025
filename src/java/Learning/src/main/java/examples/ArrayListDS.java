package examples;
import java.util.ArrayList;
import java.util.Arrays;
public class ArrayListDS {
    private ArrayList<Integer> list = new ArrayList<>();

    public void create(int value) {
        list.add(value);
    }

    public void read() {
        System.out.println("Array elements: " + list);
    }

    public void update(int index, int newValue) {
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