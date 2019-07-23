package consumerproducer;

import consumerproducer.Consumer;
import consumerproducer.EventStorage;
import consumerproducer.Producer;

import java.io.IOException;
/**
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) throws IOException {
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        Consumer consumer = new Consumer(storage);
        Thread consumerThread = new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}