package org.example;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner stlineObj = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter arithmetic expression");
        String stline = stlineObj.nextLine();
        Calc.calculate(stline);
    }
}
