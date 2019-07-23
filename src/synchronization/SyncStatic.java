package synchronization;

/**
 * @author Administrator
 */
public class SyncStatic implements Runnable{
    private static int count;
    public SyncStatic() {
        count = 0;
    }
    public synchronized static void method() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        method();
    }

    public static void main(String[] args) {
        //锁住不同对象时, 互斥
        differentObject();
    }
    private static void differentObject() {
        Thread thread1 = new Thread(new SyncStatic(), "SyncThread1");
        Thread thread2 = new Thread(new SyncStatic(), "SyncThread2");
        thread1.start();
        thread2.start();
    }
}
