package separatestartandresult;

import java.util.concurrent.*;

public class ReportProcessor implements Runnable{
    private final CompletionService<String> service;
    private volatile boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        end = false;
    }

    @Override
    public void run() {
        while (!end) {
            try {
                Future<String> result = service.poll(20, TimeUnit.SECONDS);
                if(result != null){
                    String report = result.get();
                    System.out.println(report);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    public void stopProcessing(){
        this.end = true;
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<String> service = new ExecutorCompletionService<>(executor);

        //send request
        ReportRequest faceRequest = new ReportRequest("Face",service);
        ReportRequest onlineRequest = new ReportRequest("Online",service);


        Thread faceThread = new Thread(faceRequest);
        Thread onlineThread = new Thread(onlineRequest);

        //poll result from executor
        ReportProcessor processor = new ReportProcessor(service);
        Thread senderThread = new Thread(processor);

        System.out.println("Main: Start the threads");
        faceThread.start();
        onlineThread.start();
        senderThread.start();
        try {
            faceThread.join();
            onlineThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main: shutdown the executor!");
        executor.shutdown();
        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processor.stopProcessing();
        System.out.println("Main: ends");
    }
}
