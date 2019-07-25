package createthreadexecutor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
public class Task implements Runnable{
    private final Date initDate;
    private final String name;

    public Task(String name) {
        this.initDate = new Date();
        this.name = name;
    }

    @Override
    public void run() {
        //created date
        System.out.println("Created on:"+Thread.currentThread().getName()+","+name+","+initDate);
        //started date
        System.out.println("start on:"+Thread.currentThread().getName()+","+name+","+new Date());

        Long duration =(long) (Math.random()*10);
        //task running duration
        System.out.println("doing a task during :"+Thread.currentThread().getName()+","+name+","+duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //complete date
        System.out.println("finished on:"+Thread.currentThread().getName()+","+name+","+new Date());
    }
}
