import java.util.Vector;

public class VectorDemo {
    public void performCRUD() {
        Vector<String> vector = new Vector<>();
        vector.add("Red");
        vector.add("Green");
        vector.add("Blue");
        System.out.println("Vector: " + vector);
        vector.set(1, "Yellow");
        vector.remove("Red");
        System.out.println("After Update and Delete: " + vector);
    }
}