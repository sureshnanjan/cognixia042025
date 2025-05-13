import java.util.LinkedHashSet;

public class LinkedHashSetDemo {
    public void performCRUD() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("One");
        set.add("Two");
        set.add("Three");
        System.out.println("LinkedHashSet: " + set);
        set.remove("Two");
        set.add("Four");
        System.out.println("After modifications: " + set);
    }
}