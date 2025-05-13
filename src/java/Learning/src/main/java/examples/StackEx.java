package examples;

import java.util.*;

public class StackEx {
    Stack<String> stack = new Stack<>();

    public void push(String item) {
        stack.push(item);
    }

    public void pop() {
        if (!stack.isEmpty()) {
            stack.pop();
        } else {
            System.out.println("Stack is empty.");
        }
    }

    public void peek() {
        if (!stack.isEmpty()) {
            System.out.println("Top: " + stack.peek());
        } else {
            System.out.println("Stack is empty.");
        }
    }

    public void read() {
        System.out.println("Stack: " + stack);
    }
}

