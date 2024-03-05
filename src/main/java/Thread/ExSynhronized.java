package Thread;

public class ExSynhronized {

    public static void main(String[] args) throws InterruptedException {

        Market market = new Market();
        Producer producer = new Producer(market);
        Consumer consumer = new Consumer(market);
        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
        threadProducer.start();
        threadConsumer.start();

//        threadProducer.join();
//        threadConsumer.join();
        System.out.println("Main завершен");
    }

}

class Market {
    private int breadCounter = 0;

    public synchronized void putBread() {
        while (breadCounter >= 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        breadCounter++;
        System.out.println("Был изготовлен 1 хлеб. Осталось = " + breadCounter);
        notify();
    }

    public synchronized void getBread() {
        while (breadCounter < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        breadCounter--;
        System.out.println("Был продан 1 хлеб. Осталось = " + breadCounter);
        notify();
    }

}

class Producer implements Runnable {
    Market market;
    Producer(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            market.putBread();
        }
    }
}

class Consumer implements Runnable {
    Market market;

    public Consumer(Market market) {
        this.market = market;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            market.getBread();
        }
    }
}