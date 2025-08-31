package recursion;

import java.util.Scanner;

public class CheckPalindromeUsingRecursion {

    static void checkPalindrome(int i, String inputString){

        if (i > (inputString.length()/2)-1){
            System.out.println("input string is a palindrome : "+ inputString);
            return;
        }
        //check the values are equal at the indexes symmetrical about the centre of the array.
         if (inputString.charAt(i) != inputString.charAt(inputString.length() - i - 1)){
             System.out.println("string is not a palindrome.");
             return;
         }
         checkPalindrome(i+1, inputString);
    }

    public static void main(String[] args) {
        System.out.println("enter the string you want to check the palindrome for...");
        Scanner sc = new Scanner(System.in);
        String inputString = sc.next();
        checkPalindrome(0, inputString);
    }
}
