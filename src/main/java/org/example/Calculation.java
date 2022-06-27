package org.example;

public class Calculation {
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
            if (arithmeticn.charAt(i) == 'd'){
                arithmeticsig++;
            }
        }
        char[] arithmetikchar = new char[arithmeticsig];
        int j = 0;
        for (int i = 0; i < arithmeticn.length(); i++) {
            if (arithmeticn.charAt(i) == 'd') {
                arithmetikchar[j] = sarithmetic.charAt(i);
                j++;
            }
        }
        while ((Utils.ifHaveElement(arithmetikchar, '*', '/')) && (arithmetikchar.length > 0)) {
            for (int i = 0; i < arithmetikchar.length; i++) {
                if (arithmetikchar[i] == '*' || arithmetikchar[i] == '/') {
                    if (arithmetikchar[i] == '*') {
                        floatdigit[i] = floatdigit[i] * floatdigit[i + 1];
                    }
                    if (arithmetikchar[i] == '/') {
                        floatdigit[i] = floatdigit[i] / floatdigit[i + 1];
                    }
                    arithmetikchar = Utils.removeArrayElementChar(arithmetikchar, i);
                    floatdigit = Utils.removeArrayElementFloat(floatdigit, i + 1);
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

                    arithmetikchar = Utils.removeArrayElementChar(arithmetikchar, i);
                    floatdigit = Utils.removeArrayElementFloat(floatdigit, i + 1);

                }
            }
        }
        return floatdigit[0];
    }

}
