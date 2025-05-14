package examples;

public class Searcher implements Eatable {
    int value;

    @Override
    public String toString() {
        return "Searcher{" +
                "value=" + value +
                '}';
    }

    public Searcher(int value) {
        this.value = value;
    }

    @Override
    public void Eat() {

    }

    @Override
    public void eat() {
        System.out.println("This is a SEARCHER eat");
    }


}
