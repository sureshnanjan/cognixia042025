package org.example;
import examples.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntPredicate;

import static java.lang.String.format;

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
        //functInterface();
        HashMapEx hash=new HashMapEx();
        hash.create("one","Pavi");
        hash.create("two","kavi");
        hash.read();
        hash.update("one","PPP");
        hash.read();
        hash.delete("two");
        hash.read();
        LinkedListEx link=new LinkedListEx();
        link.create("pavi");
        link.create("kavi");
        link.read();
        link.update(0,"ppp");
        link.read();
        link.delete("ppp");
        link.read();
        ArrayListEx list = new ArrayListEx();
        list.create("pavi");
        list.create("kavi");
        list.create(("vishwa"));
        list.read();
        list.update(0, "ppp");
        list.read();
        list.delete("kavi");
        list.read();
        HashSetEx set = new HashSetEx();
        set.create("pavi");
        set.create("kavi");
        set.read();
        set.update("pavi", "ppp");
        set.read();
        set.delete("ppp");
        set.read();
        TreeMapEx tree = new TreeMapEx();
        tree.create("id1", "pavi");
        tree.create("id2", "kavi");
        tree.read();
        tree.update("id1", "ppp");
        tree.read();
        tree.delete("id1");
        tree.read();
        StackEx stack = new StackEx();
        stack.push("pavi");
        stack.push("kavi");
        stack.read();
        stack.peek();
        stack.pop();
        stack.read();
        stack.pop();
        stack.pop(); // popping from empty stack
        QueueEx queue = new QueueEx();
        queue.enqueue("pavi");
        queue.enqueue("kavi");
        queue.read();
        queue.peek();
        queue.dequeue();
        queue.read();
        queue.dequeue();
        queue.dequeue(); // trying to dequeue from empty queue


        int[] numbers = {10,20,11,2,6,9,12,19,100};
        System.out.println(Arrays.stream(numbers).collect(()-> 100,(a,b)-> System.out.println("a +b"),(a,b)-> System.out.println("a * b")));
        //int[] numbers = {10,20,11,2,6,9,12,19,100};
        //System.out.println(Arrays.stream(numbers).collect(()-> 100,(a,b)-> System.out.println("a +b"),(a,b)-> System.out.println("a * b")));

        //BinarySearcherHowtoCall();
        PetItems dog = new PetItems();
        System.out.println(dog);
        Car ford = new Car();
       Bike bike = new Bike();
       Vehicle[] myvehickles = {new Bike(), new Car(), new Bike(), new Car()};

       GenericPet<SimpleClass> mygen = new GenericPet<>();


       SimpleClass.InnerClass my

       // Driver - ChromeDriver , FirefoxDriver
        // Vehicle myveh = new Vehicle();
        // Animal -  Abstract concept
        // Animal myanimal = new Animal()
        SimpleClass cls = new SimpleClass(10,"");

        // Create Read Update Delete
        Adder ad2and4 = new Adder(2,4); // Integer Adder
        ad2and4.Add();
        GenericAdder<Integer> add2and4 = new GenericAdder<>(2,4);
        GenericAdder<Float> add2_4and2_4 = new GenericAdder<>(2.4f,2.4f);
        GenericAdder<String> addoneTwo = new GenericAdder<>("One", "Two");
        GenericAdder<SimpleClass> sicl1and2 = new GenericAdder<>(
                new SimpleClass(10,"Ten"),
                new SimpleClass(20,"Twenty"));





    }

    private static void BinarySearcherHowtoCall() {
        SimpleClass[] mymembers = {new SimpleClass(0,"suresh"), new SimpleClass(1,"adesh")};
        SimpleClass myKey = new SimpleClass(10,"suresh");
        int result = Arrays.binarySearch(mymembers, myKey, new Comparator<SimpleClass>() {
            @Override
            public int compare(SimpleClass o1, SimpleClass o2) {
                return Integer.compare(o1.number,o2.number);
            }
        });
        int resultlambda = Arrays.binarySearch(mymembers, myKey, (a,b)->a.name.compareTo(b.name));
        System.out.println(result);
        System.out.println(resultlambda);
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
        Translate tamil = c -> {
            switch (c) {
                case 0: return "poojyam";
                case 1: return "Onnu";
                case 2: return "Rendu";
                case 3: return "Moondru";
                case 4: return "Naalu";
                case 5: return "Aindhu";
                case 6: return "Aaru";
                case 7: return "Yezhu";
                case 8: return "Yettu";
                case 9: return "Onbadhu";
                default: return "Vera number";
            }
        };

        Translate telugu = c -> {
            switch (c) {
                case 0: return "సున్నా";
                case 1: return "ఒకటి";
                case 2: return "రెండు";
                case 3: return "మూడు";
                case 4: return "నాలుగు";
                case 5: return "ఐదు";
                case 6: return "ఆరు";
                case 7: return "ఏడు";
                case 8: return "ఎనిమిది";
                case 9: return "తొమ్మిది";
                default: return "Telugu లో లేదు";
            }
        };

        Translate hindi = c -> {
            switch (c) {
                case 0: return "शून्य";
                case 1: return "एक";
                case 2: return "दो";
                case 3: return "तीन";
                case 4: return "चार";
                case 5: return "पांच";
                case 6: return "छह";
                case 7: return "सात";
                case 8: return "आठ";
                case 9: return "नौ";
                default: return "Hindi में नहीं है";
            }};
        // TO DO Implement a Hindi Translator

        /*
        * Interface Function<T,R>
        Type Parameters:
        * T - the type of the input to the function
        * R - the type of the result of the function
        * */

        Function<Integer, String> ftamil = input -> {
            switch (input) {
                case 0:
                    return "பூஜ்யம்";
                case 1:
                    return "ஒன்று";
                case 2:
                    return "இரண்டு";
                case 3:
                    return "மூன்று";
                case 4:
                    return "நான்கு";
                case 5:
                    return "ஐந்து";
                case 6:
                    return "ஆறு";
                case 7:
                    return "ஏழு";
                case 8:
                    return "எட்டு";
                case 9:
                    return "ஒன்பது";
                default:
                    return "வேறு ஏதாவது";
            }
        };

        for (int i = 0; i <= 9; i++) {
            System.out.println(i + " -> " + ftamil.apply(i));
        }

        System.out.println(ftamil.apply(1));

        for (int i = 0; i <= 9; i++) {
            System.out.println(format("Number: %s, Tamil: %s, Telugu: %s, Hindi: %s", i,tamil.translate(i), telugu.translate(i), hindi.translate(i)));
        }
    }

    private static void functInterface() {
        Calculate add2 = p-> p+2; // lambda expression
        Calculate double_this = d-> d * 2;
        Calculate triple_this = t -> t * 3;
        //TODO: Implement a sqare/cube function
        Calculate square_this = s -> s * s;
        Calculate cube_this = c -> c * c * c;


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
        System.out.println("Squaring ");
        System.out.println(square_this.calculate(4));
        System.out.println(square_this.calculate(5));
        System.out.println(square_this.calculate(6));
        System.out.println("Cubing ");
        System.out.println(cube_this.calculate(2));
        System.out.println(cube_this.calculate(3));
        System.out.println(cube_this.calculate(4));
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
    }
}



