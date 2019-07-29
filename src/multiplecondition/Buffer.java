package multiplecondition;

import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sunhang
 * @email sunhang@weli.cn
 */
public class Buffer {
    private final LinkedList<String> buffer;
    private final int maxSize;
    private final ReentrantLock lock;

    private final Condition lines;
    private final Condition space;

    private boolean pendingLines;

    public Buffer(int maxSize){
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }
    public void insert(String line){
        lock.lock();
        try {
            while (buffer.size() == maxSize) {
                space.await();
            }
            buffer.offer(line);
            System.out.println("buffer size:"+buffer.size());
            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public String get(){
        String line = null;
        lock.lock();
        try {
            while (buffer.size() == 0 && hasPendingLines()){
                lines.await();
            }
            if(hasPendingLines()){
                line = buffer.poll();
                space.signalAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return line;
    }
    public synchronized void setPendingLines(boolean pendingLines){
        this.pendingLines = pendingLines;
    }
    public synchronized boolean hasPendingLines(){
        return pendingLines || buffer.size()>0;
    }
}
