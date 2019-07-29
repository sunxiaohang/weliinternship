package creatingstreamfromsources;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author sunhang
 * @date 2019/7/29 11:51
 * @email sunhang@weli.cn
 */
public class MySupplier implements Supplier<String> {
    private final AtomicInteger counter;

    public MySupplier() {
        counter = new AtomicInteger(0);
    }

    @Override
    public String get() {
        int value = counter.getAndAdd(1);
        return "String "+value;
    }

    public static void main(String[] args) {
        //create a stream from a list
        List<Person> persons = PersonGenerator.generatePersonList(10000);
        Stream<Person> personStream = persons.parallelStream();
        System.out.println(personStream.count());
        Supplier<String> supplier = new MySupplier();
        Stream<String> generatorStream = Stream.generate(supplier);
        generatorStream.parallel().limit(10).forEach(s-> System.out.print(s+"\t"));
        Stream<String> elementsStream = Stream.of("Peter","John","Mary");
        elementsStream.parallel().forEach(element-> System.out.println(element));
    }
}

