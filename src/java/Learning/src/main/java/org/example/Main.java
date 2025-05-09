package org.example;
<<<<<<< HEAD
import examples.ClassMemberDemo;
import java.util.*;

=======
import examples.*;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntPredicate;

import static java.lang.String.format;
>>>>>>> 29e55bc4b4d705ec31dffba628a9c9fa6fcc2a38

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    int text;
    //float text;
    BinaryOperator<Integer> c = (param1, param2)  -> param1 + param2;
    public static void main(String[] args) {
        //add("one", "two");
        //anyMatchDemo();
        //LangTranslator();
        int[] numbers = {10,20,11,2,6,9,12,19,100};
        System.out.println(Arrays.stream(numbers).collect(()-> 100,(a,b)-> System.out.println("a +b"),(a,b)-> System.out.println("a * b")));



    }

    private static void anyMatchDemo() {
        IntPredicate even_checker = new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value /2 == 0;
            }
        };
        IntPredicate morethan100checker = new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value > 100;
            }
        };

        class EvenChecker implements IntPredicate{
            @Override
            public boolean test(int value) {
                return value /2 == 0;
            }
        }

        int[] numbers = {1,10,8,9,4,3,2,0};
        System.out.println(Arrays.stream(numbers).anyMatch(c-> c / 2 ==0));
        // Any number greater than 100
        System.out.println(Arrays.stream(numbers).anyMatch(c-> c > 100));
        System.out.println(Arrays.stream(numbers).anyMatch(even_checker));
        // Any number greater than 100
        System.out.println(Arrays.stream(numbers).anyMatch(morethan100checker));
        System.out.println(Arrays.stream(numbers).anyMatch(new EvenChecker()));
    }

    private static void LangTranslator() {
        // Methods
        //functInterface();
        Translate tamil = c -> {switch (c){
            case 1:
                return "Onnu";
            case 2:
                return  "Rendu";
            default:
                return "Vera number";
        }};

        Translate telugu = c -> {switch (c){
            case 1:
                return "ఒకటి";
            case 2:
                return  "రెండు";
            default:
                return "Tamil Number";
        }};

        // TO DO Implement a Hindi Translator

        /*
        * Interface Function<T,R>
        Type Parameters:
        * T - the type of the input to the function
        * R - the type of the result of the function
        * */

        Function<Integer,String> ftamil = input -> {switch(input){
            case 1:
                return "ஒன்று";
            case 2:
                return "இரண்டு";
            default:
                return "வேறு ஏதாவது";
        }};

        ftamil.apply(1);

        System.out.println(telugu.translate(1));
        System.out.println(telugu.translate(2));
        System.out.println(telugu.translate(3));
        System.out.println(tamil.translate(1));
        System.out.println(tamil.translate(2));
        System.out.println(tamil.translate(3));
    }

    private static void functInterface() {
        Calculate add2 = p-> p+2; // lambda expression
        Calculate double_this = d-> d * 2;
        Calculate triple_this = t -> t * 3;
        //TODO: Implement a sqare/cube function


        System.out.println("Doubling ");
        System.out.println(double_this.calculate(40));
        System.out.println(double_this.calculate(50));
        System.out.println(double_this.calculate(60));
        System.out.println("Tripling ");
        System.out.println(triple_this.calculate(40));
        System.out.println(triple_this.calculate(30));
        System.out.println(triple_this.calculate(20));
        System.out.println("Add 2  ");
        System.out.println(add2.calculate(40));
        System.out.println(add2.calculate(30));
        System.out.println(add2.calculate(20));
    }


    // Signature Name and Number and type of params
    public static void  CretePerson(){
        System.out.println("No Params");
    }
    public static void  CretePerson(String param1){
        System.out.println("I Have One String params" + param1);
    }
    public static void  CretePerson(int param1){
        System.out.println("I Have One Integer params" + param1);
    }
    public static void  CretePerson(String param1, String param2){
        System.out.println("I Have Two String params" + param1 + " : "+ param2 );
    }




    private static void literasl_demo() {
        // REpresent 100 in all the various formats
        int int100 = 100;
        int hex100 = 0x40;
        int oct100 = 0x40;//
        // Floating Point
        double doub = 123.4;
        float flt = 123.4f;
        // Bllolen
        boolean bln = true;
        boolean blnfal = false;
        // Characters
        char capA = 'A';
        char capAHex = '\u0041';
        System.out.println(capA + ":" + capAHex);

        // "When 2 and 2 is added the resulyt will be 4"
        String inter = format(
                "%s : %s", capA,capAHex);
        int i = 0;
        String mymessage =
                String.valueOf( new StringBuilder("When")
                        .append(i)
                        .append("and")
                .append(i+2)
                .append("are added the result is ")
                .append(10));

        for (int j = 0; i < 5; i++) {
            System.out.println(format("When %d and %d is added the resulyt will be %d",i,i+2,add(i,i+2)));
        }

        for (int x = 0; i < 5; i++) {
            System.out.println(new StringBuilder("When")
                    .append(i)
                    .append("and")
                    .append(i+2)
                    .append("are added the result is ")
                    .append(add(i,i+2)));

        }

        // Class , Interface , Enum
        SimpleClass cls = new SimpleClass(0,"");
        Eatable einst = cls;
        Days day = Days.monday;
    }

    private static int add(int a , int b){
        return a + b;
    }

    private static void extracted08() {
        System.out.println("Number of Instances " + ClassMemberDemo.GetInstanceCount());
        ClassMemberDemo inst1 = new ClassMemberDemo(1);
        System.out.println("Number of Instances " + ClassMemberDemo.GetInstanceCount());
        ClassMemberDemo inst2 = new ClassMemberDemo(2);
        inst1.MethodOne();
        inst2.MethodOne();
        ClassMemberDemo.MethodTwo();
        inst1.MethodOne();
        inst2.MethodOne();
        ClassMemberDemo inst3 = new ClassMemberDemo(3);
        inst3.MethodOne();
        System.out.println("Number of Instances " + ClassMemberDemo.GetInstanceCount());
<<<<<<< HEAD


        }

=======
>>>>>>> 29e55bc4b4d705ec31dffba628a9c9fa6fcc2a38
    }
}



