package armstrong;

public class ArmstrongCalculator {
    boolean isNumberArmstrong(int number) {
        int sum = 0;
        int inputNumber = number;
        if (number > 0) {
            //get the digits
            //log10 base 10 (number) gives the digits
            int digitsCount = (int) (Math.log10(number) + 1);

            while (number >0) {
                int digit = number % 10;
                sum = sum + (int) Math.pow(digit, digitsCount);
                number = number/10;
            }
        }
        System.out.println("sum value is " + sum);
        return sum == inputNumber;
    }
}
// time complexity is
// (log n base 10) + 1 roughly equals to log n base 10.