package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExSheduled {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

//        scheduledExecutorService.execute(new ScheduledRunnable());
//        scheduledExecutorService.shutdown();

//        scheduledExecutorService.schedule(new ScheduledRunnable(), 3, TimeUnit.SECONDS);
//        scheduledExecutorService.shutdown();

//        scheduledExecutorService.scheduleAtFixedRate(new ScheduledRunnable(), 3, 1, TimeUnit.SECONDS);
//        Thread.sleep(10000);
//        scheduledExecutorService.shutdown();

        scheduledExecutorService.scheduleWithFixedDelay(new ScheduledRunnable(), 3, 5, TimeUnit.SECONDS);
        Thread.sleep(100000);
        scheduledExecutorService.shutdown();

//        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            newCachedThreadPool.execute(new ScheduledRunnable());
//        }
//        Thread.sleep(10000);
//        newCachedThreadPool.shutdown();

        System.out.println("Main is ended");
    }
}

class ScheduledRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " -- начала свою работу");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " -- закончил свою работу");
    }
}