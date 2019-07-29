package jointaskresults;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author sunhang
 * @date 2019/7/29 10:39
 * @email sunhang@weli.cn
 */
public class DocumentTask extends RecursiveTask<Integer> {
    private String[][] document;
    private int start,end;
    private String word;

    public DocumentTask(String[][] document, int start, int end, String word) {
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        Integer result = null;
        if(end-start<10){
            result = processLine(document,start,end,word);
        }else{
            int mid = (start+end)/2;
            DocumentTask task1 = new DocumentTask(document,start,mid,word);
            DocumentTask task2 = new DocumentTask(document,mid,end,word);
            invokeAll(task1,task2);
            try {
                result = groupResult(task1.get(),task2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Integer groupResult(Integer integer, Integer integer1) {
        return integer+integer1;
    }

    private Integer processLine(String[][] document, int start, int end, String word) {
        List<LineTask> tasks = new ArrayList<>();
        for (int i = start; i < end; i++) {
            LineTask task = new LineTask(document[i],0,document[i].length,word);
            tasks.add(task);
        }
        invokeAll(tasks);
        int result = 0;
        for (int i = 0; i < tasks.size(); i++) {
            LineTask task = tasks.get(i);
            try {
                result = result+task.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        DocumentMock mock = new DocumentMock();
        String[][] document = mock.generateDocument(100,1000,"the");
        DocumentTask task = new DocumentTask(document,0,100,"the");
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        commonPool.execute(task);
        do{
            System.out.println("task is running...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!task.isDone());
        commonPool.shutdown();
        try {
            commonPool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}