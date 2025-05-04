package java_practice.practice;

import java.util.Scanner;

public class Solution {

    // byte->short->char->int->long->float->double
    void average(int a, int b, int c) {
        float avg = (a + b + c) / 3;
        System.out.println("Average: " + avg);
    }

    void areaOfSquare(int side) {
        int area = side * side;
        System.out.println("Area of Square: " + area);
    }

    void applyTax(float pencil, float pen, float eraser) {
        float total = pencil + pen + eraser, tax = total * 0.18F;
        System.out.println("Total cost: " + (total + tax));
        System.out.println("Tax applied: " + tax);
    }

    public static void main(String[] args) {
        Solution obj = new Solution();

        try (Scanner sc = new Scanner(System.in)) {
            // Question 1
            System.out.println("Enter 3 numbers: ");
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            obj.average(a, b, c);
            // Question 2
            System.out.println("Enter side of square: ");
            int side = sc.nextInt();
            obj.areaOfSquare(side);
            // Question 3
            System.out.println("Enter cost of pencil, pen and eraser (float): ");
            float pencil = sc.nextFloat(), pen = sc.nextFloat(), eraser = sc.nextFloat();
            obj.applyTax(pencil, pen, eraser);
        }
    }
}
