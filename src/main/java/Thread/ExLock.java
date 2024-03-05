package Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExLock {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        CashPoint cashPoint1 = new CashPoint(lock, "George");
        CashPoint cashPoint2 = new CashPoint(lock, "Rezo");
        CashPoint cashPoint3 = new CashPoint(lock, "Kostya");
        Thread.sleep(2000);
        CashPoint cashPoint4 = new CashPoint(lock, "Alexandr");
        CashPoint cashPoint5 = new CashPoint(lock, "Nikita");
        CashPoint cashPoint6 = new CashPoint(lock, "Fedor");

    }
}

class CashPoint extends Thread {
    private Lock lock;
    private String name;

    public CashPoint(Lock lock, String name) {
        this.lock = lock;
        this.name = name;
        this.start();
    }

    @Override
    public void run() {
        System.out.println(name + " стоит в очереди, чтобы воспользоваться банкоматом");
        if (lock.tryLock()) {
            System.out.println(name + " пользуется банкоматом...");
            try {
                Thread.sleep(2000);
                System.out.println(name + " закончил пользоваться банкоматом");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            System.out.println(name + " ушел домой");
        } else {
            System.out.println(name + " не дождался банкомата и ушел домой!!!");
        }
    }

}
