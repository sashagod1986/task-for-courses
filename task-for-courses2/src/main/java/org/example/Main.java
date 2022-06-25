package org.example;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.Scanner;


public class Main {
    public static char[] removeArrayElementChar(char[] needarray, int index) {
        StringBuilder sb = new StringBuilder();
        sb.append(needarray);
        sb.deleteCharAt(index);
        return sb.toString().toCharArray();
    }

    public static float[] removeArrayElementFloat(float[] needarray, int index) {
        float[] tempArray = new float[needarray.length - 1];
        for (int i = 0, k = 0; i < needarray.length; i++) {
            if (i == index) {
                continue;
            }
                tempArray[k++] = needarray[i];
        }
        return tempArray;
    }

    public static boolean ifHaveElement(char[] needarray, char element0, char element1) {
        boolean result = false;
        for (int i = 0; i < needarray.length; i++) {
            if (needarray[i] == element0 || needarray[i] == element1) result = true;
        }
        return result;
    }
    public static boolean mathematicalSymbolCheck(char[] stline){
        boolean result = true;
        for (int i = 0; i < stline.length - 1; i++) {
            if ((stline[i] == '+' || stline[i] == '-') && (stline[i + 1] == '+' || stline[i + 1] == '-' || stline[i + 1] == '*' || stline[i + 1] == '/')) {
                result = false;
            }
        }
        if (stline[0] == '+' || stline[0] == '*' || stline[0] == '/') {
            result = false;
        }
        if (stline[stline.length - 1] == '+' || stline[stline.length - 1] == '-' || stline[stline.length - 1] == '*' || stline[stline.length - 1] == '/') {
            result = false;
        }
        return result;
    }

    public static float calculate(char[] arithmetic) {
        String sarithmetic = new String(arithmetic);
        for (int i = 0; i < arithmetic.length - 1; i++) {
            if ((arithmetic[i] == '*' || arithmetic[i] == '/') && (arithmetic[i + 1] == '-')) {
                arithmetic[i] = 'd';
            }
        }
        for (int i = 1; i < arithmetic.length; i++) {
            if ((arithmetic[i] == '+' || arithmetic[i] == '-' || arithmetic[i] == '*' || arithmetic[i] == '/') && (arithmetic[i - 1] != 'd')) {
                arithmetic[i] = 'd';
            }
        }
        String arithmeticn = new String(arithmetic);
        String[] digits = arithmeticn.split("d");
        float[] floatdigit = new float[digits.length];
        for (int i = 0; i < floatdigit.length; i++) {
            floatdigit[i] = Float.parseFloat(digits[i]);
        }
        int arithmeticsig = 0;
        for (int i = 0; i < arithmeticn.length(); i++) {
            if (arithmeticn.charAt(i) == 'd') arithmeticsig++;
        }
        char[] arithmetikchar = new char[arithmeticsig];
        int j = 0;
        for (int i = 0; i < arithmeticn.length(); i++) {
            if (arithmeticn.charAt(i) == 'd') {
                arithmetikchar[j] = sarithmetic.charAt(i);
                j++;
            }
        }
        while ((ifHaveElement(arithmetikchar, '*', '/')) && (arithmetikchar.length > 0)) {
            for (int i = 0; i < arithmetikchar.length; i++) {
                if (arithmetikchar[i] == '*' || arithmetikchar[i] == '/') {
                    if (arithmetikchar[i] == '*') {
                        floatdigit[i] = floatdigit[i] * floatdigit[i + 1];
                    }
                    if (arithmetikchar[i] == '/') {
                        floatdigit[i] = floatdigit[i] / floatdigit[i + 1];
                    }
                    arithmetikchar = removeArrayElementChar(arithmetikchar, i);
                    floatdigit = removeArrayElementFloat(floatdigit, i + 1);
                }
            }
        }


        while (arithmetikchar.length > 0) {
            for (int i = 0; i < arithmetikchar.length; i++) {
                if (arithmetikchar[i] == '+' || arithmetikchar[i] == '-') {
                    if (arithmetikchar[i] == '+') {
                        floatdigit[i] = floatdigit[i] + floatdigit[i + 1];
                    }
                    if (arithmetikchar[i] == '-') {
                        floatdigit[i] = floatdigit[i] - floatdigit[i + 1];
                    }

                    arithmetikchar = removeArrayElementChar(arithmetikchar, i);
                    floatdigit = removeArrayElementFloat(floatdigit, i + 1);

                }
            }
        }
        return floatdigit[0];
    }

    public static void main(String[] args){
        Scanner stlineObj = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter arithmetic expression");
        String stline = stlineObj.nextLine();
        System.out.println("stline=" + stline);
        System.out.println("lenght=" + stline.length());
        char[] stlinechar = stline.toCharArray();


        int brackets = 0;                                           // check brackets

        while (ifHaveElement(stlinechar,'(', ')')) {
            for (int i = 0; i < stline.length(); i++) {
                if (stline.charAt(i) == '(') brackets++;
                if (stline.charAt(i) == ')') brackets--;
            }
            if (brackets == 0) {
                System.out.println("brackets ok");
                int lbracket = 0;
                int rbracket = 0;
                for (int i = 0; i < stline.length(); i++) {
                    if (stline.charAt(i) == ')'){
                        int j = i;
                        while (stline.charAt(j) != '('){
                            j--;
                        }




                        stlinechar = removeArrayElementChar(stlinechar, i);
                        stlinechar = removeArrayElementChar(stlinechar, j);
                        System.out.println();
                        System.out.print("lbracket="+j+" rbracket="+i+" ");
                        System.out.println();
                        System.out.print("stline ");
                        for (int y=0;y<stlinechar.length;y++){
                            System.out.print(stlinechar[y]);
                        }
                        System.out.println();
                        char[] tempc = new char[i-j-1];
                        for (int y=j,k=0;y<i-1;y++){
                            tempc[k]=stlinechar[y];
                            k++;
                        }
                        for (int y=0;y<tempc.length;y++){
                            System.out.print(tempc[y]);
                        }
                        System.out.println("tempc= "+tempc.length);
                        if (mathematicalSymbolCheck(tempc)) {

                            System.out.println();
                            float result = calculate(tempc);

                            System.out.println("result= "+result);
                            for (int k=j;k<i-1;k++){
                                stlinechar = removeArrayElementChar(stlinechar,j);
                            }
                            System.out.println("stlinechar=");
                            for (int y=0;y<stlinechar.length;y++){
                                System.out.print(stlinechar[y]);
                            }
                            String sresult = Float.toString(result);
                            char[] cresult = new char[stlinechar.length+sresult.length()];
                            for (int k=0;k<j;k++){
                                cresult[k]=stlinechar[k];
                            }
                            for (int k=j,h=0;h<sresult.length();h++){
                                cresult[k] = sresult.charAt(h);
                                k++;
                            }
                            for (int k=j+sresult.length(), h=j;h<stlinechar.length;h++){
                                cresult[k] = stlinechar[h];
                                k++;
                            }
                            System.out.println();
                            System.out.print("cresult= ");
                            for (int k=0;k<cresult.length;k++){
                                System.out.print(cresult[k]);
                            }
                            System.out.println();
                            stlinechar = cresult;
                            System.out.println();
                            System.out.print("stlinechar=");
                            for (int y=0;y<stlinechar.length;y++){
                                System.out.print(stlinechar[y]);
                            }
                            System.out.println();
                            System.out.print("sresult="+sresult);




                        } else System.out.println("wrong decimal");


                    }
                }



            } else {
                if (brackets > 0) System.out.println("extra parenthesis (");
                if (brackets < 0) System.out.println("extra parenthesis )");
            }
        }

            if (mathematicalSymbolCheck(stlinechar)) {

                System.out.println();
                float result = calculate(stlinechar);
                System.out.println("result= "+result);

            } else System.out.println("wrong decimal");

    }
}
