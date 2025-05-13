package examples;
import java.util.LinkedList;
import java.util.Queue;

public class QueueDS {
    public Queue<Integer> queue = new LinkedList<>();

    //Create
    public void enqueue(int val){
        queue.offer(val); //offer instead of add - Because when queue is full, add() throws an exception but offer returns false
    }

    // Read (Peek)
    public void read() {
        System.out.println("Queue: " + queue);
        if (!queue.isEmpty()) {
            System.out.println("Front element: " + queue.peek());
        }
    }

    // Update (Replace front element)
    public void update(int newValue) {
        if (!queue.isEmpty()) {
            queue.poll();
            queue.offer(newValue);
        } else {
            System.out.println("Queue is empty");
        }
    }

    // Delete (Dequeue)
    public void delete() {
        if (!queue.isEmpty()) {
            System.out.println("Removed: " + queue.poll());
        } else {
            System.out.println("Queue is empty");
        }
    }
}
