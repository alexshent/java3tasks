package alexshent.assignment.first;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class CatalanProducerTest {

    @Test
    void getNumberIndexIsPositive() {
        // given
        long n = 30;
        BigInteger expected = BigInteger.valueOf(3814986502092304L);
        // when
        BigInteger actual = CatalanProducer.getNumber(n);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void getNumberIndexIsZero() {
        // given
        long n = 0;
        BigInteger expected = BigInteger.valueOf(1);
        // when
        BigInteger actual = CatalanProducer.getNumber(n);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void getNumberIndexIsNegative() {
        // given
        long n = -1;
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            BigInteger actual = CatalanProducer.getNumber(n);
        });
    }

    @Test
    void getNumberIndexIsOfMaxValue() {
        // given
        long n = (Long.MAX_VALUE + 2) / 4;
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            BigInteger actual = CatalanProducer.getNumber(n);
        });
    }

    @Test
    void producerConsumer() throws InterruptedException {
        // given
        long n = 3L;
        List<ProducerConsumerMessage> expected = new ArrayList<>();
        expected.add(new ProducerConsumerMessage(1, BigInteger.ONE));
        expected.add(new ProducerConsumerMessage(2, BigInteger.valueOf(2L)));
        expected.add(new ProducerConsumerMessage(3, BigInteger.valueOf(5L)));
        expected.add(new ProducerConsumerMessage(0, BigInteger.valueOf(0L)));

        Queue<ProducerConsumerMessage> queue = new LinkedList<>();
        Thread producerThread = new Thread(new CatalanProducer(queue, n));
        CatalanConsumer catalanConsumer = new CatalanConsumer(queue, true);
        Thread consumerThread = new Thread(catalanConsumer);
        producerThread.start();
        consumerThread.start();
        producerThread.join();
        consumerThread.join();
        List<ProducerConsumerMessage> actual = catalanConsumer.getList();

        assertEquals(expected, actual);
    }
}