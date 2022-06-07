package alexshent.assignment.third;

import java.math.BigInteger;

/**
 * Calculate sum of the number decimal digits
 */
public class Number {
    /**
     * Calculate sum of decimal digits of the number
     *
     * @param number biginteger number
     * @return sum of the number decimal digits
     */
    public static BigInteger sumOfDigits(BigInteger number) {
        if (number == null) {
            throw new IllegalArgumentException(Messages.MSG_SUM_NULL_NUMBER);
        }
        if (number.signum() == -1) {
            throw new IllegalArgumentException(String.format(Messages.MSG_SUM_NEGATIVE_NUMBER, number));
        }
        BigInteger result = BigInteger.ZERO;
        while (number.signum() == 1) {
            result = result.add(number.remainder(BigInteger.TEN));
            number = number.divide(BigInteger.TEN);
        }
        return result;
    }
}
