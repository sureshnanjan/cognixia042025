package examples;
import java.util.*;

public class QueueCRUD {

    private Queue<String> queue = new LinkedList<>();

    // CREATE - Enqueue
    public void create(String item) {
        queue.offer(item);
        System.out.println("Enqueued: " + item);
    }

    // READ - View entire queue
    public void read() {
        System.out.println("Queue: " + queue);
    }

    // UPDATE - Update element at a specific position (non-standard for queue)
    public void update(int index, String newItem) {
        if (index < 0 || index >= queue.size()) {
            System.out.println("Invalid index.");
            return;
        }
        List<String> tempList = new ArrayList<>(queue);
        String oldItem = tempList.set(index, newItem);
        queue.clear();
        queue.addAll(tempList);
        System.out.println("Updated item '" + oldItem + "' to '" + newItem + "'");
    }

    // DELETE - Dequeue (remove front)
    public void delete() {
        String removed = queue.poll();
        if (removed != null) {
            System.out.println("Dequeued: " + removed);
        } else {
            System.out.println("Queue is empty.");
        }
    }

    // DELETE - Remove specific item
    public void delete(String item) {
        if (queue.remove(item)) {
            System.out.println("Removed item: " + item);
        } else {
            System.out.println("Item not found: " + item);
        }
    }

    // MAIN method for testing
    public static void main(String[] args) {
        QueueCRUD qc = new QueueCRUD();

        // CREATE
        qc.create("Task1");
        qc.create("Task2");
        qc.create("Task3");

        // READ
        qc.read();

        // UPDATE
        qc.update(1, "UpdatedTask2");
        qc.read();

        // DELETE front
        qc.delete();
        qc.read();

        // DELETE specific
        qc.delete("Task3");
        qc.read();
    }
}
