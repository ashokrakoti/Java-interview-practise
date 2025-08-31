package armstrong;

public class Main {
    public static void main(String[] args) {
        int a = 15;
        ArmstrongCalculator armstrongCalculator = new ArmstrongCalculator();

        System.out.println("isGiven Number is Armstrong: " + armstrongCalculator.isNumberArmstrong(a));
    }
}