package Learning.src.main.java.examples;

public abstract class Vehicle {
    int num_of_tyres;
    int engine_cc;

    String fuel_type;

    public void Turn(String direction){
        switch (direction){
            case "right":
                System.out.println("Turning Right");
            case "left":
                System.out.println("Turning Left");
        }
        SimpleClass cls = new SimpleClass(10,"simple");

    }

    public  void Stop(){}

    public  void Move(){}

    public void Start(){}

    public abstract void Park();

    public final void DoRegistration(){
        System.out.println("All Vehicles should be registerd in RTO");
    }
}
