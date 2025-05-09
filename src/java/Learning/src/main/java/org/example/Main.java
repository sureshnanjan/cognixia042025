package org.example;
import examples.ClassMemberDemo;
import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //oldCode();
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



