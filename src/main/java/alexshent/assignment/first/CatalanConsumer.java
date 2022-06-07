package alexshent.assignment.first;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CatalanConsumer implements Runnable {
    private final Queue<ProducerConsumerMessage> queue;
    private List<ProducerConsumerMessage> list = new ArrayList<>();
    private boolean saveToList;

    public CatalanConsumer(Queue<ProducerConsumerMessage> queue, boolean saveToList) {
        this.queue = queue;
        this.saveToList = saveToList;
    }

    public List<ProducerConsumerMessage> getList() {
        return list;
    }

    /**
     * print queue messages from the producer
     */
    @Override
    public void run() {
        final ProducerConsumerMessage finishProducerConsumerMessage = new ProducerConsumerMessage(0L, BigInteger.ZERO);

        while (true) {
            synchronized (queue) {
                while (!queue.isEmpty()) {
                    ProducerConsumerMessage producerConsumerMessage = queue.remove();
                    if (saveToList) {
                        list.add(producerConsumerMessage);
                    }
                    if (producerConsumerMessage.equals(finishProducerConsumerMessage)) {
                        return;
                    }
                    System.out.printf(Messages.MSG_RESULT,
                            producerConsumerMessage.index(),
                            producerConsumerMessage.catalanNumber().toString()
                    );
                }
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
