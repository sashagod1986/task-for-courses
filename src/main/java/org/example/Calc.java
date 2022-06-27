package org.example;

public class Calc {
    public static void calculate(String stline) {
        stline = stline.replaceAll(" ","");
        stline = stline.replaceAll("\\)\\(",")*(");
        stline = stline.replaceAll("\\+\\-","-");
        int digits = 0;
        char[] stlinechar = stline.toCharArray();
        if (Utils.ifHaveElement(stlinechar, '(', ')')) {
            int brackets = 0;                                           // check brackets
            boolean bracketsB = true;
            boolean bracketsE = true;
            for (int i = 0; i < stlinechar.length-1; i++) {
                if (stlinechar[i] == '(' && stlinechar[i+1] == ')'){
                    bracketsE = false;
                }
            }
            if (stlinechar.length <= 3){
                for (int i=0;i<stlinechar.length-2;i++){
                    if(stlinechar[i] == '(' && !Character.isDigit(stlinechar[i+1]) && stlinechar[i+2] == ')'){
                        bracketsE = false;
                    }
                }
            }
            for (int i = 0; i < stlinechar.length; i++) {
                if (stlinechar[i] == '('){
                    brackets++;
                }
                if (stlinechar[i] == ')'){
                    brackets--;
                }
                if (brackets < 0){
                    bracketsB = false;
                }
            }
            if (brackets == 0 && bracketsB && bracketsE) {
                digits = Utils.digitsQuantity(stline);
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
                if (brackets > 0){
                    System.out.println("extra parenthesis (");
                }
                if (brackets < 0){
                    System.out.println("extra parenthesis )");
                }
                if (!bracketsE){
                    System.out.println("empty brackets");
                }

            }
        } else {
            if (Utils.mathematicalSymbolCheck(stlinechar)) {
                digits = Utils.digitsQuantity(stline);
                float result = Calculation.calculate(stlinechar);
                System.out.println("result= " + result);
                System.out.println("digits quantity= "+digits);
            } else System.out.println("wrong decimal");
        }
    }
}
