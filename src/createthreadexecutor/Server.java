package createthreadexecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author sunhang
 * @email sunhang@weli.cn
 */
public class Server {
    private final ThreadPoolExecutor executor;
    public Server(){
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        RejectedTaskController controller = new RejectedTaskController();
        executor.setRejectedExecutionHandler(controller);
    }
    public void executeTask(Task task){
        System.out.println("server: A new task has arrived\n");
        executor.execute(task);
        System.out.println(executor.getPoolSize());
        System.out.println(executor.getActiveCount());
        System.out.println(executor.getTaskCount());
        System.out.println(executor.getCompletedTaskCount());
    }
    public void endServer(){
        executor.shutdown();
    }

    public static void main(String[] args) {
        Server server = new Server();
        System.out.println("Main: starting.");
        for (int i = 0; i < 100; i++) {
            Task task = new Task("task "+i);
            server.executeTask(task);
        }
        System.out.println("Main: shutdown the executor.");
        server.endServer();
        System.out.println("sending another task");
        Task task = new Task("Rejected task");
        server.executeTask(task);
        System.out.println("Main:end");
    }
}
