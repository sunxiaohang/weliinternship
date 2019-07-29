package customizingthreadpoolexecutor;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author sunhang
 * @date 2019/7/29 14:14
 * @email sunhang@weli.cn
 */
public class MyExecutor extends ThreadPoolExecutor {
    private final ConcurrentHashMap<Runnable, Date> startTimes;
    public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        startTimes = new ConcurrentHashMap<>();
    }
    /**
     * override shutdown
     * */
    @Override
    public void shutdown() {
        System.out.println(getCompletedTaskCount());
        System.out.println(getActiveCount());
        System.out.println(getQueue().size());
        super.shutdown();
    }

    /**
     * override shutdown not
     * */
    @Override
    public List<Runnable> shutdownNow() {
        System.out.println(getCompletedTaskCount());
        System.out.println(getActiveCount());
        System.out.println(getQueue().size());
        return super.shutdownNow();
    }

    /**
     * override beforeExecute
     * */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println(t.getName()+","+r.hashCode());
        startTimes.put(r,new Date());
    }

    /**
     * override afterExecute
     * */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Future<?> result = (Future<?>) r;
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Date startDate = startTimes.remove(r);
        Date finishDate = new Date();
        long diff = finishDate.getTime()-startDate.getTime();
        System.out.println(diff);
        super.afterExecute(r, t);
    }
}
