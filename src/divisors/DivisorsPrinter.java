package divisors;

public class DivisorsPrinter {
    public static void main(String[] args) {
        int number  = 21;
        System.out.println("the divisors of the number "+ number + "are: ");
        printDivisors(number);
    }

    static void printDivisors(int number) {
        if (number > 0) {
            for ( int i =1 ; i< number/2; i++) {
                if(number % i == 0)
                    System.out.println(i);
            }
        }
    }
}
