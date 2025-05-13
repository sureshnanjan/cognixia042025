package Learning.src.main.java.examples;

public class GenericAdder<T1> {
    T1 first;
    T1 second;
    public GenericAdder(T1 a, T1 b ) {
        this.first = a;
        this.second = b;

    }
    public void Add(){
        System.out.println(String.format("%s : %s", this.first.getClass(), this.second.getClass()));;
    }

}

