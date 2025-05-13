package examples;

import java.util.*;

public class QueueEx {
    Queue<String> queue = new LinkedList<>();

    public void enqueue(String item) {
        queue.add(item);
    }

    public void dequeue() {
        if (!queue.isEmpty()) {
            queue.remove();
        } else {
            System.out.println("Queue is empty.");
        }
    }

    public void peek() {
        if (!queue.isEmpty()) {
            System.out.println("Front: " + queue.peek());
        } else {
            System.out.println("Queue is empty.");
        }
    }

    public void read() {
        System.out.println("Queue: " + queue);
    }
}

