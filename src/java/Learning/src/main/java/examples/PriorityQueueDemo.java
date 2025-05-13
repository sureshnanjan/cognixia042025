import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public void performCRUD() {
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("C");
        queue.add("A");
        queue.add("B");
        System.out.println("PriorityQueue: " + queue);
        queue.remove("A");
        queue.add("D");
        System.out.println("After modifications: " + queue);
    }
}