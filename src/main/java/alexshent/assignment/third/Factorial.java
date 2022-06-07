package alexshent.assignment.third;

import java.math.BigInteger;

/**
 * Factorial calculation
 */
public class Factorial {
    /**
     * Calculate factorial for integer value
     *
     * @param n factorial parameter
     * @return calculated factorial value
     */
    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(String.format(Messages.MSG_FACTORIAL_NEGATIVE_ARGUMENT, n));
        }
        BigInteger result = BigInteger.ONE;
        if (n == 0 || n == 1) {
            return result;
        }
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
