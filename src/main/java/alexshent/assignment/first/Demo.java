package alexshent.assignment.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Demo {
    public static void main(String[] args) {
        Queue<ProducerConsumerMessage> queue = new LinkedList<>();
        System.out.println(Messages.MSG_INDEX_INPUT);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            long n = Long.parseLong(br.readLine());
            CatalanProducer catalanProducer = new CatalanProducer(queue, n);
            CatalanConsumer catalanConsumer = new CatalanConsumer(queue, false);
            new Thread(catalanProducer).start();
            new Thread(catalanConsumer).start();
        } catch (NumberFormatException exception) {
            System.out.println(Messages.MSG_INVALID_NUMBER);
        } catch (IOException exception) {
            System.out.println(Messages.MSG_IO_ERROR);
        }
    }
}
