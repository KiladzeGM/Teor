package Thread;

public class ExDaemon {
    public static void main(String[] args) {
        System.out.println("Main начал свою работу!");
        Thread thread = new UserTread();
        thread.setName("User_Thread");
        Thread thread2 = new DaemonThread();
        thread2.setName("Daemon_Thread");
        thread.start();
        thread2.setDaemon(true);
        thread2.start();
        System.out.println("Main закончил свою работу!");
    }
}

class UserTread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " начал свою работу!");
        for (char i = 'A'; i <= 'J'; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + " закончил свою работу!");
    }
}

class DaemonThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " начал свою работу!");
        for (int i = 1; i < 20; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + " закончил свою работу!");
    }
}
