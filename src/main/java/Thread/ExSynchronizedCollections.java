package Thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExSynchronizedCollections {
    public static void main(String[] args) throws InterruptedException {
       List<Integer> list = new ArrayList<>();
       for (int i = 0; i < 10; i++) {
           list.add(i);
       }
       //List<Integer> result = new ArrayList<>();
       List<Integer> result = Collections.synchronizedList(new ArrayList<>());
       Runnable runnable = () -> {result.addAll(list);};
       Thread thread1 = new Thread(runnable);
       Thread thread2 = new Thread(runnable);
       thread1.start();
       thread2.start();
       thread1.join();
       thread2.join();
       System.out.println(result);
    }
}
