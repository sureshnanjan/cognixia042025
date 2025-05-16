package examples;

<<<<<<< HEAD
public class Car {
    // Attributes  WHAT is a car
    private int engine_power;
    private int fuel;
    private int traction;
    String color;

    // Methods Behaviours - What it can DO
    void Drive(){}
    void Turn(){}
    void Stop(){}


=======

public class Car extends  Vehicle{
    @MySpecialMethod
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
>>>>>>> d25743bf309d5049166cac980ff42460cdbc23ad
}
