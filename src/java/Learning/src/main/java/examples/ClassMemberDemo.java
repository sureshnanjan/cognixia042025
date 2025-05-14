package Learning.src.main.java.examples;

public class ClassMemberDemo {
    static int number_of_instances = 0;
    final float PI = 22/7f; //
    public ClassMemberDemo(int field_int) {
        this.field_int = field_int;
        number_of_instances ++;
    }

    // Members
        // Static or Instance
        // Fields - Variables
        int field_int; // Primitive
        Car mycar; // Custom
        // Methods - Behaviours
        static int class_var;
    public void MethodOne(){
        System.out.println("This is my method executed :" + this.field_int++ );
        System.out.println("This is my second method : " + class_var++);
    }

    public static void MethodTwo(){
        System.out.println("This is my static method");
    }

    public static int GetInstanceCount(){
        return  number_of_instances;
    }
}
