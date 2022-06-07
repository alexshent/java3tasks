package alexshent.assignment.third;

import java.math.BigInteger;

/**
 * Demo class
 */
public class Demo {
    public static void main(String[] args) {
        final int n = 100;
        BigInteger factorial = Factorial.factorial(n);
        System.out.printf(Messages.MSG_FACTORIAL_RESULT, n, factorial.toString());
        BigInteger result = Number.sumOfDigits(factorial);
        System.out.printf(Messages.MSG_SUM_RESULT, result.toString());
    }
}
