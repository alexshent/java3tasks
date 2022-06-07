package alexshent.assignment.first;

import java.math.BigInteger;
import java.util.Queue;

/**
 * Catalan number
 */
public class CatalanProducer implements Runnable {
    private final Queue<ProducerConsumerMessage> queue;
    private final long index;

    public CatalanProducer(Queue<ProducerConsumerMessage> queue, long index) {
        this.queue = queue;
        this.index = index;
    }

    private static BigInteger getIterationNumber(BigInteger catalanNumber, long n) {
        return catalanNumber.multiply(
                BigInteger.valueOf(4L * n + 2)
        ).divide(
                BigInteger.valueOf(n + 2)
        );
    }

    /**
     * Get Catalan numbers, producer-consumer multithreading version
     */
    @Override
    public void run() {
        if (index < 0 || index > (Long.MAX_VALUE - 2) / 4) {
            throw new IllegalArgumentException(String.format(Messages.MSG_INVALID_CATALAN_SEQUENCE_INDEX, index));
        }
        BigInteger result = BigInteger.ONE;
        for (int n = 0; n < index; n++) {
            result = getIterationNumber(result, n);
            ProducerConsumerMessage producerConsumerMessage = new ProducerConsumerMessage(n + 1, result);
            synchronized (queue) {
                queue.add(producerConsumerMessage);
                queue.notify();
            }
        }
        synchronized (queue) {
            queue.add(new ProducerConsumerMessage(0L, BigInteger.ZERO));
        }
    }

    /**
     * Get number of correct parentheses expressions with the length 2n (n open and n close brackets),
     * using Catalan number
     * <p>
     * n = 0 empty => C(0) = 1
     * n = 1 () => C(1) = 1
     * n = 2 ()() , (()) => C(2) = 2
     * n = 3 ((())) , (()()) , (())() , ()(()) , ()()() => C(3) = 5
     *
     * @param index - Catalan sequence index
     * @return Catalan number
     */
    public static BigInteger getNumber(long index) {
        if (index < 0 || index > (Long.MAX_VALUE - 2) / 4) {
            throw new IllegalArgumentException(String.format(Messages.MSG_INVALID_CATALAN_SEQUENCE_INDEX, index));
        }
        BigInteger result = BigInteger.ONE;
        for (int n = 0; n < index; n++) {
            result = getIterationNumber(result, n);
        }
        return result;
    }
}
