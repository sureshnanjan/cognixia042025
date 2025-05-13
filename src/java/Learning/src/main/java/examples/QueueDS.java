package examples;
import java.util.LinkedList;
import java.util.Queue;

public class QueueDS {
    private Queue<String> queue = new LinkedList<>();

   public void create(String value) {
        queue.offer(value);
}

public void read() {
        System.out.println("Queue: " + queue);
        if (!queue.isEmpty()) {
        System.out.println("Front element: " + queue.peek());
        }
        }

      public void update(String newValue) {
        if (!queue.isEmpty()) {
        queue.poll();
            queue.offer(newValue);
        } else {
                System.out.println("Queue is empty");
        }
                }

         public void delete() {
                if (!queue.isEmpty()) {
        System.out.println("Removed: " + queue.poll());
        } else {
        System.out.println("Queue is empty");
        }
                }
                }




