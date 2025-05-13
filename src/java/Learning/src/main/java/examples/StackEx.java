package examples;

import java.util.*;

public class StackEx {
    Stack<String> stack = new Stack<>();

    public void create(String item) {
        stack.push(item);
    }

    public void read() {
        System.out.println("Stack: " + stack);
    }

    public void update(String oldItem, String newItem) {
        if (stack.contains(oldItem)) {
            Stack<String> temp = new Stack<>();
            while (!stack.isEmpty()) {
                String top = stack.pop();
                if (top.equals(oldItem)) {
                    temp.push(newItem);
                    break;
                } else {
                    temp.push(top);
                }
            }
            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
        } else {
            System.out.println("Item not found in stack.");
        }
    }

    public void delete() {
        if (!stack.isEmpty()) {
            String removed = stack.pop();
            System.out.println("Deleted from stack: " + removed);
        } else {
            System.out.println("Stack is empty.");
        }
    }
}
