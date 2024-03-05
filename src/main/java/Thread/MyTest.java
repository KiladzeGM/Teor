package Thread;

public class MyTest {

    public static void main(String[] args) {
        C c = new C();
        Thread2 thread2 = new Thread2(c);
        Thread3 thread3 = new Thread3(c);
        thread2.start();
        thread3.start();
    }
}

class C {
    public int count = 0;
    public int abc = 0;

    public synchronized void inc() {
        System.out.println(++count + " = count -- " + Thread2.currentThread().getName());
    }

    public synchronized void a() {
        System.out.println(++abc + " = abc -- " + Thread2.currentThread().getName());
    }

}

class Thread2 extends Thread {
    public C c;

    Thread2(C c) {
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            c.inc();
            System.out.println(++c.abc + " --- " + Thread.currentThread().getName());
        }
    }
}

class Thread3 extends Thread {
    public C c;

    Thread3(C c) {
        this.c = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            c.inc();
            System.out.println(++c.abc + " --- " + Thread.currentThread().getName());
        }
    }
}