package examples;

import java.util.*;

public class QueueEx {
    Queue<String> queue = new LinkedList<>();

    public void create(String item) {
        queue.offer(item);
    }

    public void read() {
        System.out.println("Queue: " + queue);
    }

    public void update(String oldItem, String newItem) {
        if (queue.contains(oldItem)) {
            queue.remove(oldItem);
            queue.offer(newItem);
        } else {
            System.out.println("Item not found in queue.");
        }
    }

    public void delete() {
        String removed = queue.poll();
        System.out.println("Deleted: " + removed);
    }
}
