import java.util.ArrayDeque;

public class ArrayDequeDemo {
    public void performCRUD() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.add("Alpha");
        deque.add("Beta");
        deque.add("Gamma");
        System.out.println("ArrayDeque: " + deque);
        deque.remove("Alpha");
        deque.add("Delta");
        System.out.println("After modifications: " + deque);
    }
}