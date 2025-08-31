package arrays;

public class findLargestNumberInArray {
    static int[] numArr = {37464, 34478, 3, 5, 6, 883, 35, 7, 79};

    public static void main(String[] args) {
        int largest = findLargestInArray(numArr);
        System.out.println("largest value is :" + largest  );
    }

    private static int findLargestInArray(int [] arr) {
        int greatest = Integer.MIN_VALUE;
        for (int i : arr){
            if (i > greatest){
                greatest = i;
            }
        }
        return greatest;
    }

}
/*
time complexity= O(N)
 */
