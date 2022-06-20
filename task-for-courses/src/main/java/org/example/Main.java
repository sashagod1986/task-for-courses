package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ArithmeticObj = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter arithmetic expression");
        System.out.println("new courses");
        String arithmetic = ArithmeticObj.nextLine();
        System.out.println("arithmetic="+arithmetic);

        int a=Character.getNumericValue(arithmetic.charAt(0));
        System.out.println("first="+a);
        System.out.println("lenght="+arithmetic.length());
        int slenght = arithmetic.length();
        System.out.println("slenght="+slenght);
        System.out.println("intarrey=");
        char chararrey[] = new char[slenght];
        for (int i=0;i<slenght;i++){
            if (arithmetic.charAt(i) != '+' && arithmetic.charAt(i) != '*' && arithmetic.charAt(i) != '/' && arithmetic.charAt(i) != '(' && arithmetic.charAt(i) != ')'){

                chararrey[i]=arithmetic.charAt(i);

            }
            else chararrey[i]='|';
        }
        System.out.print(Arrays.toString(chararrey));
        int numbarrey[] = new int[slenght];
        System.out.println("digit=");
        for (int i=0;i<slenght;i++){
            if (chararrey[i] == '-' || chararrey[i] == '|') {
                System.out.print("i=" + i + ",");
            }
        }


    }
}
