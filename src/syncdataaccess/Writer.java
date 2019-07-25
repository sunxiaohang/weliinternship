package syncdataaccess;

import java.util.Date;

/**
 * @author Administrator
 */
public class Writer implements Runnable{
    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        System.out.println("Writer: Attempt to modify the prices "+new Date());
        pricesInfo.setPrices(Math.random()*10,Math.random()*10);
        System.out.println("Writer: prices has been modified "+new Date());
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();
        Reader[] readers = new Reader[5];
        Thread[] threadReaders = new Thread[5];
        for (int i = 0; i < readers.length; i++) {
            readers[i] = new Reader(pricesInfo);
            threadReaders[i] = new Thread(readers[i]);
        }
        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);
        for (int i = 0; i < readers.length; i++) {
            threadReaders[i].start();
        }
        threadWriter.start();
    }
}
