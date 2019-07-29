package createthreadexecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author sunhang
 * @email sunhang@weli.cn
 */
public class RejectedTaskController implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString());
        System.out.println(executor.toString());
        System.out.println(executor.isTerminating());
        System.out.println(executor.isTerminated());
    }
}
