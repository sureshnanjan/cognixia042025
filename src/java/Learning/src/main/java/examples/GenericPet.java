package examples;

import java.util.Arrays;

public class GenericPet<T1 extends Comparable> {

    T1[] mypets;

    public GenericPet() {
        //this.mypets = mypets;
        Arrays.sort(mypets);
    }
}
