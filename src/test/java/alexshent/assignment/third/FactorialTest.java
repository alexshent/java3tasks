package alexshent.assignment.third;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {
    @Test
    public void testFactorialForZero() {
        // given
        int n = 0;
        BigInteger expected = BigInteger.ONE;
        // when
        BigInteger actual = Factorial.factorial(n);
        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testFactorialForOne() {
        // given
        int n = 1;
        BigInteger expected = BigInteger.ONE;
        // when
        BigInteger actual = Factorial.factorial(n);
        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testFactorialForFour() {
        // given
        int n = 4;
        BigInteger expected = BigInteger.valueOf(24);
        // when
        BigInteger actual = Factorial.factorial(n);
        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testFactorialForNegativeInteger() {
        // given
        int n = -2;
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            BigInteger actual = Factorial.factorial(n);
        });
    }
}