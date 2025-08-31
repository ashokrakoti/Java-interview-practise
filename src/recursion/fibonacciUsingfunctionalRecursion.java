package recursion;


class fibonacciUsingFunctionalRecursion {

    static int fibonacci(int N){

        // Base Condition.
        if(N <= 1){
            return N;
        }
        return fibonacci(N-1) + fibonacci(N-2);
    }

    public static void main(String[] args) {
        // Here, let’s take the value of N to be 4.
        int N = 10;
        System.out.println(fibonacci(N));
    }
}
