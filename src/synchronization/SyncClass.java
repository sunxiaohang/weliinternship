package synchronization;

/**
 * @author Administrator
 */
public class SyncClass implements Runnable{
    private static int count;
    public SyncClass() {
        count = 0;
    }
    @Override
    public void run() {
        synchronized (SyncClass.class) {
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
        //锁住不同对象时, 互斥
        differentObject();
    }
    private static void differentObject() {
        Thread thread1 = new Thread(new SyncClass(), "SyncThread1");
        Thread thread2 = new Thread(new SyncClass(), "SyncThread2");
        thread1.start();
        thread2.start();
    }
}
