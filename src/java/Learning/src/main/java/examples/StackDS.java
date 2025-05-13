package examples;
import java.util.Stack;

public class StackDS {
    public Stack<Integer> stackVar = new Stack<>();

    //create value
    public void create(int value){
        stackVar.push(value);
    }

    //read - Peek
    public void read(){
        System.out.println("Stack values: " + stackVar);
        if(stackVar.isEmpty()){
            System.out.println("Top element: " + stackVar.peek());
        }
    }

    //update new value
    public void update(int newValue){
        if(!stackVar.isEmpty()){
            stackVar.pop();
            stackVar.push(newValue);
        }else{
            System.out.println("Stack is empty");
        }
    }

    //delete item
    public void delete(){
        if(!stackVar.isEmpty()){
            System.out.println("Popped: " + stackVar.pop());
        }else{
            System.out.println("Stack is empty");
        }
    }


}
