package Learning.src.main.java.examples;


public class Car extends  Vehicle{
    @examples.MySpecialMethod
    int myvalue;
    public Car() {
        super();
    }


    @Override
    public void Start() {
        System.out.println("Turn Ignition , start the car");
    }

    @Override
    public void Park() {

    }
}
