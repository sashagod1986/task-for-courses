package org.example;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.Scanner;


public class Main {
    public static char[] removeArrayElementChar(char[] needarray, int index){
            StringBuilder sb = new StringBuilder();
            sb.append(needarray);
            sb.deleteCharAt(index);
            return sb.toString().toCharArray();
    }
    public static float[] removeArrayElementFloat(float[] needarray, int index){
        float[] tempArray = new float[needarray.length-1];
        for (int i = 0, k = 0;i<needarray.length; i++) {
            if (i == index) {
                continue;
            } else tempArray[k++] = needarray[i];
        }
        return tempArray;
    }

    public static boolean ifHaveElement(char[] needarray, char element0, char element1){
        boolean result = false;
        for (int i=0;i<needarray.length;i++) {
            if (needarray[i] == element0 || needarray[i] == element1) result = true;
        }
        return result;
    }


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
        if ((arithmetic.charAt(0) == '+' || arithmetic.charAt(0) == '*' || arithmetic.charAt(0) == '/') && (arithmetic.charAt(1) == '+' || arithmetic.charAt(1) == '-' || arithmetic.charAt(1) == '*' || arithmetic.charAt(1) == '/')){
            arithmeticsigns = false;
        }
        if (arithmetic.charAt(arithmetic.length()-1) == '+' || arithmetic.charAt(arithmetic.length()-1) == '-' || arithmetic.charAt(arithmetic.length()-1) == '*' || arithmetic.charAt(arithmetic.length()-1) == '/'){
            arithmeticsigns = false;
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
                floatdigit[i] = Float.parseFloat(digits[i]);
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


            for (int i=0;i<arithmetikchar.length;i++){
                System.out.print(" arithmetic="+arithmetikchar[i]+" ");
            }
            System.out.println();
            System.out.println(" arithmetic lenght="+arithmetikchar.length+" ");


            if (ifHaveElement(arithmetikchar, '*', '/'))  System.out.println(" arithmetikchar have * or /");
            while ((ifHaveElement(arithmetikchar, '*', '/')) && (arithmetikchar.length > 0) ){
                for (int i = 0; i < arithmetikchar.length; i++) {
                    if (arithmetikchar[i] == '*' || arithmetikchar[i] == '/') {
                        if (arithmetikchar[i] == '*'){
                            floatdigit[i]=floatdigit[i]*floatdigit[i+1];
                            System.out.print(" float "+i+" ="+floatdigit[i]+" ");
                        }
                        if (arithmetikchar[i] == '/'){
                            floatdigit[i]=floatdigit[i]/floatdigit[i+1];
                            System.out.print(" float "+i+" ="+floatdigit[i]+" ");
                        }
                        arithmetikchar = removeArrayElementChar(arithmetikchar,i);
                        floatdigit = removeArrayElementFloat(floatdigit,i+1);
                    }
                }

            }
            while (arithmetikchar.length > 0){
                for (int i = 0; i < arithmetikchar.length; i++) {
                    if (arithmetikchar[i] == '+' || arithmetikchar[i] == '-') {
                        if (arithmetikchar[i] == '+'){
                            floatdigit[i]=floatdigit[i]+floatdigit[i+1];
                            System.out.print(" float "+i+" ="+floatdigit[i]+" ");
                        }
                        if (arithmetikchar[i] == '-'){
                            floatdigit[i]=floatdigit[i]-floatdigit[i+1];
                            System.out.print(" float "+i+" ="+floatdigit[i]+" ");
                        }
                        arithmetikchar = removeArrayElementChar(arithmetikchar,i);
                        floatdigit = removeArrayElementFloat(floatdigit,i+1);
                    }
                }

            }
            System.out.println();
            for(int i=0;i<floatdigit.length;i++){
                System.out.print(" float="+floatdigit[i]+" ");
            }
            System.out.println();
            for(int i=0;i<arithmetikchar.length;i++){
                System.out.print(" arithmetic="+arithmetikchar[i]+" ");
            }

        } else System.out.println("arithmetic signs bad");





    }
}
