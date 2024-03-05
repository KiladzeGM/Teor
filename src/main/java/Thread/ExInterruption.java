package Thread;

import java.util.concurrent.ExecutorService;

public class ExInterruption {
    public static void main(String[] args) throws InterruptedException {
        MyInterruption myInterruption1 = new MyInterruption();
        myInterruption1.start();
        Thread.sleep(100);
        myInterruption1.interrupt();

    }
}

class MyInterruption extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " поток начал выполнять действие");
        for (int i = 0; i < 100; i++) {
            if (isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " поток был прерван!");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " = " + i);
            try {
                sleep(15);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " -- этот поток хотят прерывать во время сна!!!");
                return;
            }
        }
        System.out.println(Thread.currentThread().getName() + " поток завершил работу");
    }
}