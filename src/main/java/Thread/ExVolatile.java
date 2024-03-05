package Thread;

public class ExVolatile {

    public static void main(String[] args) throws InterruptedException {
        ExVolatileRunnable exVolatileRunnable = new ExVolatileRunnable();
        Thread thread = new Thread(exVolatileRunnable);
        thread.start();
        Thread.sleep(3000);
        exVolatileRunnable.b = false;
        thread.join();
        System.out.println("Main is ended");
    }

}

class ExVolatileRunnable implements Runnable {

    volatile boolean b = true;
    int count = 0;

    @Override
    public void run() {
        while (b) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() + " " + count);
    }
}
