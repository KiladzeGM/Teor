package Thread;

import java.util.concurrent.CountDownLatch;

public class ExCountDownLatch {

    static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void staffCameToWork() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("staff came to work");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }

    public static void everythingIsReady() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("everything is ready");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }

    public static void marketIsOpen() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("market is open");
        countDownLatch.countDown();
        System.out.println("countDownLatch = " + countDownLatch.getCount());
    }

    public static void main(String[] args) throws InterruptedException {
        new Friends("George", countDownLatch);
        new Friends("Rezo", countDownLatch);
        new Friends("Kostya", countDownLatch);
        new Friends("Alex", countDownLatch);
        new Friends("Michal", countDownLatch);
        new Friends("Nikita", countDownLatch);

        staffCameToWork();
        everythingIsReady();
        marketIsOpen();
    }
}

class Friends extends Thread {
    String name;
    CountDownLatch countDownLatch;

    Friends(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
        this.start();
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() + " присутпил к покупкам!");
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}