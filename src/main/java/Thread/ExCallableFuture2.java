package Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExCallableFuture2 {
    private static long sum = 0;
    private static long value = 1_000_000_000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Long>> futureList = new ArrayList<>();
        long partValue = value / 10;
        for (int i = 0; i < 10; i++) {
            long from = i * partValue + 1;
            long to = (i + 1) * partValue;
            futureList.add(executorService.submit(new CallableFuture2(from, to)));
        }
        for (Future f :
                futureList) {
            System.out.println(Thread.currentThread().getName() + " -- " + f.get());
        }
        for (Future<Long> f :
                futureList) {
            sum += f.get();
        }
        executorService.shutdown();
        System.out.println("Конечная сумма равна = " + sum);
    }
}

class CallableFuture2 implements Callable {

    long from;
    long to;
    long result = 0;

    public CallableFuture2(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Long call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " -- начал подсчет");
        for (long i = from; i <= to; i++) {
            result += i;
        }
        System.out.println(Thread.currentThread().getName() + " -- закончил подсчет");
        return result;
    }
}