package arrays;

public class SecondLargestInArray {


    public static void main(String[] args) {
        int [] numArr = {2, 34, 45, 0, 434, 89, 910, 101};
//        int [] numArr = {1,3,4,5,67,2,3,56,8};
//        int [] numArr = {1,1,1,1,1,1,1,1,2,2,2,2,2,2};
//        int [] numArr = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        int secondlargestelement = findSecondLargest(numArr);
        if (secondlargestelement == Integer.MIN_VALUE){
            System.out.println("Second largest element not present");
        }else {
            System.out.println("second largest element is: "+ secondlargestelement );
        }
        int secondSmallestElement = findSecondSmallestElement(numArr);
        if (secondSmallestElement == Integer.MAX_VALUE) {
            System.out.println("Second smallest element not present");
        } else {
            System.out.println("second smallest element is " + secondSmallestElement);
        }
    }

    public static int findSecondLargest(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for(int ele : arr) {
            if( ele > largest ) {
                secondLargest =largest;
                largest = ele;
            }else if (ele < largest && ele > secondLargest)
                secondLargest = ele;
        }
        return secondLargest;
    }

    public static int findSecondSmallestElement (int[] arr) {
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        for (int ele : arr) {
            if (ele < smallest) {
                secondSmallest = smallest;
                smallest = ele;
            }else if (ele > smallest && ele < secondSmallest)
                secondSmallest = ele;
        }
        return secondSmallest;
    }
}
//time complexity : O(N) as we are iterating over the array elements.
//space complexity: O(N) for the whole logic.
//                  O(1) for the computation logic, as we are only using 2 integer mem