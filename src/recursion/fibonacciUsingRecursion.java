package recursion;

import java.util.Arrays;
import java.util.Scanner;

public class fibonacciUsingRecursion {
    public static void main(String[] args) {
        System.out.println("enter the number of digits you want in the fibonacci series..");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        if (n<1) {
            System.out.println("invalid series input... please enter a number higher than 0....");
            return;
        }
        int[] fibArray = new int [n];
        for (int i = 0; i < n; i++) {
            fibArray[i] = fibonacci(i); // Compute each Fibonacci number recursively
        }
        System.out.println("fibonacci series built is: " + Arrays.toString(fibArray));
    }

    static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2); // Multiple recursive calls
    }
}
