package org.example;

public class ParseBrackets {
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
        stlinechar = Utils.removeArrayElementChar(stlinechar, rbracket);
        stlinechar = Utils.removeArrayElementChar(stlinechar, lbracket);
        char[] tempc = new char[rbracket - lbracket - 1];
        for (int y = lbracket, k = 0; y < rbracket - 1; y++) {
            tempc[k] = stlinechar[y];
            k++;
        }
        if (Utils.mathematicalSymbolCheck(tempc)) {
            float result = Calculation.calculate(tempc);
            for (int k = lbracket; k < rbracket - 1; k++) {
                stlinechar = Utils.removeArrayElementChar(stlinechar, lbracket);
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
        }
        return stlinechar;
    }
}
