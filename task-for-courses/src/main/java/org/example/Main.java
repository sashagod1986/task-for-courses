package org.example;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner ArithmeticObj = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter arithmetic expression");
        String arithmetic = ArithmeticObj.nextLine();
        System.out.println("arithmetic=" + arithmetic);
        System.out.println("lenght=" + arithmetic.length());
        int brackets = 0;                                           // check brackets
        for (int i = 0; i < arithmetic.length(); i++) {
            if (arithmetic.charAt(i) == '(') brackets++;
            if (arithmetic.charAt(i) == ')') brackets--;
        }
        if (brackets > 0) System.out.println("extra parenthesis (");
        if (brackets < 0) System.out.println("extra parenthesis )");
        if (brackets == 0) System.out.println("brackets ok");

        char[] arithmetichar = arithmetic.toCharArray();

        boolean arithmeticsigns = true;
        for (int i = 0; i < arithmetic.length() - 1; i++) {
            if ((arithmetic.charAt(i) == '+' || arithmetic.charAt(i) == '-') && (arithmetic.charAt(i + 1) == '+' || arithmetic.charAt(i + 1) == '-' || arithmetic.charAt(i + 1) == '*' || arithmetic.charAt(i + 1) == '/')) {
                System.out.print(" i=" + i + " ");
                System.out.print(" i=" + arithmetichar[i] + " ");
                System.out.println(" i+1=" + arithmetichar[i + 1] + " ");
                arithmeticsigns = false;
            }
        }
        if (arithmeticsigns) {
            System.out.println("arithmetic signs ok");


            for (int i = 0; i < arithmetic.length() - 1; i++) {
                if ((arithmetic.charAt(i) == '*' || arithmetic.charAt(i) == '/') && (arithmetic.charAt(i + 1) == '-')) {
                    arithmetichar[i] = 'd';
                }
            }
            System.out.println("arithmetichar=" + new String(arithmetichar));
            for (int i = 1; i < arithmetic.length(); i++) {
                if ((arithmetichar[i] == '+' || arithmetichar[i] == '-' || arithmetichar[i] == '*' || arithmetichar[i] == '/') && (arithmetichar[i - 1] != 'd')) {
                    arithmetichar[i] = 'd';

                }
            }
            System.out.println();

            String arithmeticn =  new String(arithmetichar);
            System.out.println("arithmetic=" + arithmetic);
            System.out.println("arithmeticn=" + arithmeticn);
            String[] digits = arithmeticn.split("[d]");

            float[] floatdigit = new float[digits.length];
            for(int i=0;i<floatdigit.length;i++){
                floatdigit[i] = Float.valueOf(digits[i]);
                System.out.print(" float="+floatdigit[i]+" ");
            }
            System.out.println();
            System.out.print(" float lenght="+floatdigit.length+" ");
            System.out.println();
            int arithmeticsig = 0;
            for (int i=0;i<arithmeticn.length();i++){
                if (arithmeticn.charAt(i) == 'd') arithmeticsig++;
            }
            char[] arithmetikchar = new char[arithmeticsig];
            int j=0;
            for (int i=0;i<arithmeticn.length();i++){
                if (arithmeticn.charAt(i) == 'd') {
                    arithmetikchar[j] = arithmetic.charAt(i);
                    j++;
                }
            }


            if (floatdigit.length == arithmetikchar.length) {
                char[] temparray = new char[arithmetikchar.length-1];
                for (int i=0;i<arithmetikchar.length;i++){
                    temparray[i] = arithmetikchar[i];
                }
                arithmetikchar = temparray;
            }
            for (int i=0;i<arithmetikchar.length;i++){
                System.out.print(" arithmetic="+arithmetikchar[i]+" ");
            }
            System.out.println();
            System.out.print(" arithmetic lenght="+arithmeticsig+" ");

        } else System.out.println("arithmetic signs bad");

    }
}
