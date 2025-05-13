import java.util.LinkedList;

public class LinkedListDemo {
    public void performCRUD() {
        LinkedList<String> list = new LinkedList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        System.out.println("LinkedList: " + list);
        list.set(1, "Twenty");
        list.remove("One");
        System.out.println("After Update and Delete: " + list);
    }
}

