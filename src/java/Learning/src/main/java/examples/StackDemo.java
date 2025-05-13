import java.util.Stack;

public class StackDemo {
    public void performCRUD() {
        Stack<String> stack = new Stack<>();
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        System.out.println("Stack: " + stack);
        stack.set(1, "UpdatedSecond");
        stack.remove("First");
        System.out.println("After Update and Delete: " + stack);
    }
}