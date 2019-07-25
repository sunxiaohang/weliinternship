package uncaughtedexception;

/**
 * @author Administrator
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace(System.out);
    }

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
        // default uncaught exception handler
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    }
}

