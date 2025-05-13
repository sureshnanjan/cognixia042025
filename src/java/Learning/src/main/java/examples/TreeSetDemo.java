import java.util.TreeSet;

public class TreeSetDemo {
    public void performCRUD() {
        TreeSet<String> set = new TreeSet<>();
        set.add("Zebra");
        set.add("Lion");
        set.add("Elephant");
        System.out.println("TreeSet: " + set);
        set.remove("Lion");
        set.add("Monkey");
        System.out.println("After modifications: " + set);
    }
}