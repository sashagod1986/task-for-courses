package org.example;

public class Utils {
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
            if (needarray[i] == element0 || needarray[i] == element1){
                result = true;
            }
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
        stline = new String(stlinechar);
        boolean havedd = true;
        while (havedd){
            havedd = false;
            stline = stline.replaceAll("dd","d");
            for (int i=0;i<stline.length()-1;i++){
                if (stline.charAt(i) == 'd' && stline.charAt(i+1) == 'd') havedd = true;
            }
        }

        stlinechar = stline.toCharArray();
        if (stlinechar[0]=='d'){
            stlinechar = removeArrayElementChar(stlinechar, 0);
        }
        if (stlinechar[stlinechar.length-1]=='d'){
            stlinechar = removeArrayElementChar(stlinechar, 0);
        }
        stline = new String(stlinechar);
        String[] digits = stline.split("d");
        return digits.length;
    }
}
