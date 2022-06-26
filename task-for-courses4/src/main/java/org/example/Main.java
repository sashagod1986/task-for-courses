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
    public static int digitsQuantity(String stline){
        char[] stlinechar = stline.toCharArray();
        for (int i=0;i<stlinechar.length;i++){
            if (!Character.isDigit(stlinechar[i]) && stlinechar[i] != '.' ){
                stlinechar[i] = 'd';
            }
        }
        if (stlinechar[0]=='d') stlinechar = removeArrayElementChar(stlinechar, 0);
        stline = new String(stlinechar);
        stline = stline.replaceAll("dd","d");
        String[] digits = stline.split("d");
        return digits.length;
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

    public static char[] executeBrackets(char[] stlinechar){
            int lbracket = 0;
            int rbracket = 0;
            for (int i = 0; i < stlinechar.length; i++) {
                if (stlinechar[i] == ')') {
                    int j = i;
                    while (stlinechar[j] != '(') {
                        j--;
                    }
                    lbracket = j;
                    rbracket = i;
                    i = stlinechar.length;
                }
            }
                    stlinechar = removeArrayElementChar(stlinechar, rbracket);
                    stlinechar = removeArrayElementChar(stlinechar, lbracket);
                    char[] tempc = new char[rbracket - lbracket - 1];
                    for (int y = lbracket, k = 0; y < rbracket - 1; y++) {
                        tempc[k] = stlinechar[y];
                        k++;
                    }
                    if (mathematicalSymbolCheck(tempc)) {
                        float result = calculate(tempc);
                        for (int k = lbracket; k < rbracket - 1; k++) {
                            stlinechar = removeArrayElementChar(stlinechar, lbracket);
                        }
                        String sresult = Float.toString(result);
                        char[] lresult = new char[lbracket];
                        for (int k = 0; k < lbracket; k++) {
                            lresult[k] = stlinechar[k];
                        }
                        char[] rresult = new char[stlinechar.length - lbracket];
                        for (int k = lbracket, y = 0; k < stlinechar.length; k++) {
                            rresult[y] = stlinechar[k];
                            y++;
                        }
                        String lsresult = new String(lresult);
                        String rsresult = new String(rresult);
                        String summary = lsresult + sresult + rsresult;
                        stlinechar = summary.toCharArray();
                    } else System.out.println("wrong decimal");
        return stlinechar;
    }

    public static void main(String[] args) {
        Scanner stlineObj = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter arithmetic expression");
        String stline = stlineObj.nextLine();
        int digits = digitsQuantity(stline);
        char[] stlinechar = stline.toCharArray();
            if (ifHaveElement(stlinechar, '(', ')')) {
                int brackets = 0;                                           // check brackets
                for (int i = 0; i < stlinechar.length; i++) {
                    if (stlinechar[i] == '(') brackets++;
                    if (stlinechar[i] == ')') brackets--;
                }
                if (brackets == 0) {
                while (ifHaveElement(stlinechar, '(', ')')) {
                    stlinechar = executeBrackets(stlinechar);
                }
                    if (mathematicalSymbolCheck(stlinechar)) {
                        float result = calculate(stlinechar);
                        System.out.println("result= " + result);
                        System.out.println("digits quantity= "+digits);
                    } else System.out.println("wrong decimal");
                } else {
                    if (brackets > 0) System.out.println("extra parenthesis (");
                    if (brackets < 0) System.out.println("extra parenthesis )");
                }
                } else {
                    if (mathematicalSymbolCheck(stlinechar)) {
                        float result = calculate(stlinechar);
                        System.out.println("result= " + result);
                        System.out.println("digits quantity= "+digits);
                    } else System.out.println("wrong decimal");
                }
    }
}
