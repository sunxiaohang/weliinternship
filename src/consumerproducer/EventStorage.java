package consumerproducer;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author sunhang
 * @email sunhang@weli.cn
 */
public class EventStorage {
    private int maxSize;
    private Queue<Date> storage;

    public EventStorage() {
        this.maxSize = 10;
        this.storage = new LinkedList<>();
    }
    /**
     * synchronized set method and notify inner
     * */
    public synchronized void set() {
        while (storage.size() == maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.offer(new Date());
        System.out.println("set:"+storage.size());
        notify();
    }
    public synchronized void get() {
        while (storage.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notify();
    }
}
