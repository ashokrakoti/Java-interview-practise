package recursion;

import java.util.Scanner;

public class Print1ToNUsingBackTracking {

    static void recursionUsingBacktracking(int i, int n){
       //i from n-1 to 0
        //printing the diff of n and i.
        if (i==0) return;

        recursionUsingBacktracking(i-1, n);
        System.out.println(i);
    }

    public static void main(String[] args) {
        //int n =10;//
        System.out.println("Enter the number till which you want to print");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        recursionUsingBacktracking(n, n);

    }
}
