package forkjoinpool;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author sunhang
 * @email sunhang@weli.cn
 */
public class Task extends RecursiveAction {
    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        //every fork thread process only 10 compute task
        if(last - first<10){
            updatePrices();
        }else{
            //divide task to several smaller task
            int middle = (last+first)/2;
            System.out.println(getQueuedTaskCount());
            Task t1 = new Task(products,first,middle+1,increment);
            Task t2 = new Task(products,middle+1,last,increment);
            invokeAll(t1,t2);
        }
    }

    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice()*(1+increment));
        }
    }

    public static void main(String[] args) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(10000);
        Task task = new Task(products,0,products.size(),0.20);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        do{
            System.out.print("Main: working...");
        }while (!task.isDone());
        if(task.isCompletedNormally()){
            System.out.println("done success");
        }
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if(product.getPrice()!=12){
                System.out.println(product.getName()+","+product.getPrice());
            }
        }
        System.out.println("End");
    }
}
