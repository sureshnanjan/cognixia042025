import java.util.ArrayList;

public class ArrayListDemo {
    public void performCRUD() {
        ArrayList<String> list = new ArrayList<>();

        // CREATE
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // READ
        System.out.println("ArrayList: " + list);

        // UPDATE
        list.set(1, "Blueberry");

        // DELETE
        list.remove("Apple");

        System.out.println("After Update and Delete: " + list);
    }
}