package Thread;

import java.util.concurrent.Semaphore;

public class ExSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        new CallBox("George", semaphore);
        new CallBox("Rezo", semaphore);
        new CallBox("Kostya", semaphore);
        new CallBox("Alex", semaphore);
        new CallBox("Michal", semaphore);
        new CallBox("Nikita", semaphore);
    }
}

class CallBox extends Thread {

    private String name;
    private Semaphore semaphore;

    public CallBox(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
        this.start();
    }

    @Override
    public void run() {
        System.out.println(name + " ждет свою очередь");
        try {
            semaphore.acquire();
            System.out.println(name + " говорит по телефону...");
            sleep(2000);
            System.out.println(name + " закончил использовать телефон");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
