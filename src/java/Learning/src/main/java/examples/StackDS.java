package examples;

import java.util.Stack;
public class StackDS {
    private Stack<Integer> stack = new Stack<>();

    // Create (Push)
    public void create(int value) {
        stack.push(value);
}

// Read (Peek)
public void read() {
        System.out.println("Stack: " + stack);
        if (!stack.isEmpty()) {
        System.out.println("Top element: " + stack.peek());
        }
        }

        // Update (Update top)
    public void update(int newValue) {
        if (!stack.isEmpty()) {
        stack.pop();
            stack.push(newValue);
        } else {
                System.out.println("Stack is empty");
        }
                }

                // Delete (Pop)
    public void delete() {
                if (!stack.isEmpty()) {
        System.out.println("Popped: " + stack.pop());
        } else {
        System.out.println("Stack is empty");
        }
                }
                }
