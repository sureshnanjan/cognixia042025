package examples;

public class Adder {
    int first;
    int second;

    public Adder(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int Add(){
        return  this.first + this.second;
    }
}
