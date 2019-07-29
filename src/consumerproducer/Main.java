package consumerproducer;

import java.io.IOException;
/**
 * @author sunhang
 * @email sunhang@weli.cn
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