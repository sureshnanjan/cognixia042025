public class LargestNumber {
    public static void main(String[] args) {
        int a = 15, b = 25, c = 20;
        int largest = a;

        if (b > largest) {
            largest = b;
        }
        if (c > largest) {
            largest = c;
        }

        System.out.println("Largest number is: " + largest);
    }
}
