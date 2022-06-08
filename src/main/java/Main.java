import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;


public class Main {
    public static List<Integer> shop1 = Arrays.asList(5000, 17000, 50000);
    public static List<Integer> shop2 = Arrays.asList(50000, 78000, 100000);
    public static List<Integer> shop3 = Arrays.asList(10000, 25000, 70000);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors());
        LongAdder stat = new LongAdder();
        shop1.stream()
                .forEach(i -> executorService.submit(() -> stat.add(i)));
        shop2.stream()
                .forEach(i -> executorService.submit(() -> stat.add(i)));
        shop3.stream()
                .forEach(i -> executorService.submit(() -> stat.add(i)));

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("Сумма сданной выручки по трем магазинам составляет: " + stat.sum() + " руб.");
        executorService.shutdown();
    }
}
