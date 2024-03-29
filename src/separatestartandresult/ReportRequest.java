package separatestartandresult;

import java.util.concurrent.CompletionService;
/**
 * @author sunhang
 * @email sunhang@weli.cn
 */
public class ReportRequest implements Runnable{
    private final String name;
    private final CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenerator reportGenerator = new ReportGenerator(name,"Report");
        service.submit(reportGenerator);
    }
}
