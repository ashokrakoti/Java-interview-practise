package gcd;

//find  the GCD of given two numbers
public class Main {
    public static void main(String[] args) {
        int a =0;
        int b = 75;
        GCDCalculator gcdCalculator = new GCDCalculator();
        int gcd = gcdCalculator.findGCD(a, b);
        System.out.println("gcd value calculated is: "  + gcd);
    }
}