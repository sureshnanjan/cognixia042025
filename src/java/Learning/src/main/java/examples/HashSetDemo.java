import java.util.HashSet;

public class HashSetDemo {
    public void performCRUD() {
        HashSet<String> set = new HashSet<>();
        set.add("Dog");
        set.add("Cat");
        set.add("Bird");
        System.out.println("HashSet: " + set);
        set.remove("Cat");
        set.add("Fish");
        System.out.println("After modifications: " + set);
    }
}