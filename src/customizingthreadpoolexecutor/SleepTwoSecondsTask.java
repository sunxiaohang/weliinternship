package customizingthreadpoolexecutor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author sunhang
 * @date 2019/7/29 14:23
 * @email sunhang@weli.cn
 */
public class SleepTwoSecondsTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return new Date().toString();
    }

    public static void main(String[] args) {
        MyExecutor executor = new MyExecutor(4,8,1000,TimeUnit.MICROSECONDS,new LinkedBlockingDeque<Runnable>());
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SleepTwoSecondsTask task = new SleepTwoSecondsTask();
            Future<String> result = executor.submit(task);
            results.add(result);
        }
        for (int i = 0; i < 5; i++) {
            try {
                String result = results.get(i).get();
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        for (int i = 5; i < 10; i++) {
            try {
                String result = results.get(i).get();
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
