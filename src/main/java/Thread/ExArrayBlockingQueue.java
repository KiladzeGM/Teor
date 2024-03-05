package Thread;

import java.util.concurrent.ArrayBlockingQueue;

public class ExArrayBlockingQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(4);

        //Producer
        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    queue.put(++i);
                    Thread.sleep(3000);
                    System.out.println("Producer create -- " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        //Costumer
        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    i = queue.take();
                    Thread.sleep(9000);
                    System.out.println("Costumer take -- " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
