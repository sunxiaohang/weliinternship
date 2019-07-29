package separatestartandresult;

import java.util.concurrent.Callable;
/**
 * @author sunhang
 * @email sunhang@weli.cn
 */
public class ReportGenerator implements Callable<String> {
    private final String sender;
    private final String title;

    public ReportGenerator(String sender, String title) {
        this.sender = sender;
        this.title = title;
    }

    @Override
    public String call() throws Exception {
        System.out.println(this.sender+","+this.title);
        String ret = sender+":"+title;
        return ret;
    }
}
