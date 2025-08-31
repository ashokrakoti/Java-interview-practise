package recursion;

import java.util.Arrays;

public class ReverseArrayUsingRecursion {
    static void reverseArrayUsingRecursion(int startIndex, int endIndex, int[] inputArr){
        //i=0 --> arrSize/2 -2 , i++
        //j=arrSize-1 --> arrSize/2 - 1 , j--
        //base condition
        if (startIndex==((inputArr.length/2)-1)){
            return;
        }
        //element swapping logic
        swapValues(startIndex, endIndex, inputArr);

        reverseArrayUsingRecursion(startIndex+1, endIndex-1, inputArr);
    }

    private static void swapValues(int startIndex, int endIndex, int[] inputArr) {
        inputArr[startIndex] = inputArr[startIndex] + inputArr[endIndex];
        inputArr[endIndex] = inputArr[startIndex] - inputArr[endIndex];
        inputArr[startIndex] = inputArr[startIndex] - inputArr[endIndex];
    }

    public static void main(String[] args) {
        int[] inputArr = {1,2,3,4,5,6,7};
        System.out.println("array before reversing is "+ Arrays.toString(inputArr));
        reverseArrayUsingRecursion(0, inputArr.length-1, inputArr);
        System.out.println("reversed array is " + Arrays.toString(inputArr));
    }

}
