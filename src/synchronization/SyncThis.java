package synchronization;

/**
 * @author Administrator
 */
public class SyncThis implements Runnable{
    private static int count;
    public SyncThis() {
        count = 0;
    }
    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        //锁住相同对象时, 互斥
        sameObject();
        //锁住不同对象时, 不互斥可以并发，存在安全性问题
        differentObject();
    }
    private static void differentObject() {
        Thread thread1 = new Thread(new SyncThis(), "SyncThread1");
        Thread thread2 = new Thread(new SyncThis(), "SyncThread2");
        thread1.start();
        thread2.start();
    }

    private static void sameObject() {
        SyncThis syncThis = new SyncThis();
        Thread thread1 = new Thread(syncThis, "SyncThread1");
        Thread thread2 = new Thread(syncThis, "SyncThread2");
        thread1.start();
        thread2.start();
    }
}
