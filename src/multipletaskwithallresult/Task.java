package multipletaskwithallresult;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Administrator
 */
public class Task implements Callable<Result> {
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public Result call() throws Exception {
        Thread.sleep(2000);
        int value = 0;
        for (int i = 0; i < 5; i++) {
            value+=new Random().nextInt(10);
        }
        Result result = new Result();
        result.setName(this.name);
        result.setValue(value);
        return result;
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Task> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Task task = new Task("Task-"+i);
            lists.add(task);
        }
        List<Future<Result>> resultList = null;

        try {
            resultList = executor.invokeAll(lists);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println("Main: printing the results");
        for (int i = 0; i < resultList.size(); i++) {
            Future<Result> future = resultList.get(i);
            try {
                Result result = future.get();
                System.out.println(result.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
