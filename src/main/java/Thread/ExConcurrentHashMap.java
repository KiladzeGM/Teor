package Thread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ExConcurrentHashMap {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap<>();
        hashMap.put(1, 100);
        hashMap.put(2, 200);
        hashMap.put(3, 300);
        hashMap.put(4, 400);
        hashMap.put(5, 500);
        Runnable runnable1 = () -> {
            Iterator iterator = hashMap.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(iterator.next());
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hashMap.put(6, 600);
        };

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(hashMap);
    }
}
