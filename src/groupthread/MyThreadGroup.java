package groupthread;

/**
 * @author sunhang
 * @email sunhang@weli.cn
 */
public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    public static void main(String[] args) {
        MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
        Task task = new Task();
        for(int i = 0;i<10;i++) {
            Thread t = new Thread(threadGroup, task);
            t.start();
        }
        threadGroup.list();
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread:"+Thread.currentThread().getName());
    }
}