package multipletaskwithfirstresult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sunhang
 * @email sunhang@weli.cn
 */
public class ValidatorTask implements Callable<String> {
    private final UserValidator validator;
    private final String user;
    private final String password;

    public ValidatorTask(UserValidator validator, String user, String password) {
        this.validator = validator;
        this.user = user;
        this.password = password;
    }

    @Override
    public String call() throws Exception {
        if(!validator.validate(user,password)){
            System.out.println("validate failed!");
            return null;
        }else {
            System.out.println("validate success.");
            return validator.getName();
        }
    }

    public static void main(String[] args) {
        String username = "test";
        String password = "test";
        UserValidator dapValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("DB");

        ValidatorTask dapTask = new ValidatorTask(dapValidator,username,password);
        ValidatorTask dbTask = new ValidatorTask(dbValidator,username,password);

        List<ValidatorTask> taskList = new ArrayList<>();
        taskList.add(dapTask);
        taskList.add(dbTask);

        ExecutorService executorService = Executors.newCachedThreadPool();
        String result;
        try {
            result = executorService.invokeAny(taskList);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
