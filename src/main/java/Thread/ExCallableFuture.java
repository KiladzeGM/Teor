package Thread;

import java.util.concurrent.*;

public class ExCallableFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new CallableFuture(5));
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.out.println(e.getCause());
        }
        finally {
            executorService.shutdown();
        }
    }
}

class CallableFuture implements Callable<Integer> {

    int f;

    public CallableFuture(int f) {
        this.f = f;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if (f == 0) {
            throw new Exception("Вы ввели неправильное число!");
        }
        for (int i = 1; i <= f; i++) {
            result *= i;
        }
        return result;
    }
}