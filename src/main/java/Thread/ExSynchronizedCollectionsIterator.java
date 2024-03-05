package Thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ExSynchronizedCollectionsIterator {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        //List<Integer> result = new ArrayList<>();
        List<Integer> result = Collections.synchronizedList(list);
        Runnable runnable1 = () -> {
            synchronized (result) {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
        };

        Runnable runnable2 = () -> {
            result.remove(5);
        };
        Thread thread1 = new Thread(runnable2);
        Thread thread2 = new Thread(runnable1);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(result);
    }
}
