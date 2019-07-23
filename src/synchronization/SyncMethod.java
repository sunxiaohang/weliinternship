package synchronization;

/**
 * @author Administrator
 */
public class SyncMethod implements Runnable{
    private static int count;
    public SyncMethod() {
        count = 0;
    }
    @Override
    public void run() {
        //相同方法 互斥
        add();
        //不同方法
        reduce();
    }
    private synchronized void add(){
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-add:" + (++count));
        }
    }
    private synchronized void reduce(){
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-reduce:" + (--count));
        }
    }
    public static void main(String[] args) {
        SyncMethod syncThis = new SyncMethod();
        Thread thread1 = new Thread(syncThis, "SyncThread1");
        Thread thread2 = new Thread(syncThis, "SyncThread2");
        thread1.start();
        thread2.start();
    }
}
