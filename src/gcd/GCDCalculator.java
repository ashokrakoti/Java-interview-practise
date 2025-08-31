package gcd;

public class GCDCalculator {

    int findGCD(int a, int b) {
        while (a> 0 && b>0) {
            for(int i = Math.min(a,b); i >= 1; i--) {
                if (a % i ==0 && b % i == 0) {
                    return i;
                }
            }
        }
        return 0;
    }
}

// time complexity
// O(min(a,b))
//space complexity
// O(1)
//Eucledian algos