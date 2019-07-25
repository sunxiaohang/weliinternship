package syncdataaccess;

import java.util.Date;

/**
 * @author Administrator
 */
public class Reader implements Runnable{
    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("Price 1:\n"+new Date()+":"+Thread.currentThread().getName()+","+pricesInfo.getPrice1());
            System.out.println("Price 2:\n"+new Date()+":"+Thread.currentThread().getName()+","+pricesInfo.getPrice2());
        }
    }
}
