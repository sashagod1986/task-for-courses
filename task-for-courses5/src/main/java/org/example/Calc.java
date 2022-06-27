package org.example;

public class Calc {
    public static void calculate(String stline) {
        stline = stline.replaceAll(" ","");
        stline = stline.replaceAll("\\)\\(",")*(");
        int digits = Utils.digitsQuantity(stline);
        char[] stlinechar = stline.toCharArray();
        if (Utils.ifHaveElement(stlinechar, '(', ')')) {
            int brackets = 0;                                           // check brackets
            boolean bracketsB = true;
            for (int i = 0; i < stlinechar.length; i++) {
                if (stlinechar[i] == '(') brackets++;
                if (stlinechar[i] == ')') brackets--;
                if (brackets < 0) bracketsB = false;
            }
            if (brackets == 0 && bracketsB) {
                while (Utils.ifHaveElement(stlinechar, '(', ')')) {
                    stlinechar = ParseBrackets.executeBrackets(stlinechar);
                }
                if (Utils.mathematicalSymbolCheck(stlinechar)) {
                    float result = Calculation.calculate(stlinechar);
                    System.out.println("result= " + result);
                    System.out.println("digits quantity= "+digits);
                } else System.out.println("wrong decimal");
            } else {
                System.out.println("wrong brackets");
                if (brackets > 0) System.out.println("extra parenthesis (");
                if (brackets < 0) System.out.println("extra parenthesis )");

            }
        } else {
            if (Utils.mathematicalSymbolCheck(stlinechar)) {
                float result = Calculation.calculate(stlinechar);
                System.out.println("result= " + result);
                System.out.println("digits quantity= "+digits);
            } else System.out.println("wrong decimal");
        }
    }
}
