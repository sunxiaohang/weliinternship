package multiplecondition;
import java.util.Random;
/**
 * @author Administrator
 */
public class Consumer implements Runnable{
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()){
            String line = buffer.get();
            processLine(line);
        }
    }
    private void processLine(String line){
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileMock mock = new FileMock(100,10);
        Buffer buffer = new Buffer(20);
        Producer producer = new Producer(mock,buffer);
        Thread producerThread = new Thread(producer,"Producer");

        Consumer[] consumers = new Consumer[3];
        Thread[] consumerThreads = new Thread[3];
        for (int i = 0; i < consumerThreads.length; i++) {
            consumers[i] = new Consumer(buffer);
            consumerThreads[i] = new Thread(consumers[i],"Consumers");
        }
        producerThread.start();
        for (int i = 0; i < consumerThreads.length; i++) {
            consumerThreads[i].start();
        }
    }
}
