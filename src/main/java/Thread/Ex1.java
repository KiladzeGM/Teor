package Thread;

public class Ex1 {

    public static void main(String[] args) throws InterruptedException {
        MyThread firstThread = new MyThread();
        Thread first = new Thread(firstThread);
        Thread second = new Thread(firstThread);
        Thread third = new Thread(firstThread);
        first.start();
        second.start();
        third.start();

//        first.join();
//        second.join();
        System.out.println("Main завершен!");

    }

}

class Counter {
    public static int count = 0;
}

class MyThread implements Runnable {

    public void show() {
        System.out.println(Thread.currentThread().getName());
    }

    public synchronized void increment() {
        show();
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " I was made by my parents! = " + Counter.count++);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            increment();
        }
    }
}
