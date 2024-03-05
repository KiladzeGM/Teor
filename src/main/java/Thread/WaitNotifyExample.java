package Thread;

public class WaitNotifyExample {
    public static void main(String[] args) {
        MarketNew marketNew = new MarketNew();
        ProducerNew producerNew = new ProducerNew(marketNew);
        ConsumerNew consumerNew = new ConsumerNew(marketNew);
        Thread thread1 = new Thread(producerNew);
        Thread thread2 = new Thread(consumerNew);
        thread1.start();
        thread2.start();
    }
}

class MarketNew {
    private int breadCount = 0;

    public synchronized void getBread() {
        while (breadCount < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        breadCount--;
        System.out.println("Потребитель купил 1 хлеб");
        System.out.println("Количество хлеба в магазине = " + breadCount);
        notify();
    }

    public synchronized void putBread() {
        while (breadCount >= 5) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        breadCount++;
        System.out.println("Производитель добавил на ветрину 1 хлеб");
        System.out.println("Количество хлеба в магазине = " + breadCount);
        notify();
    }
}

class ProducerNew implements Runnable {
    MarketNew marketNew;

    ProducerNew(MarketNew marketNew) {
        this.marketNew = marketNew;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            marketNew.putBread();
        }
    }
}

class ConsumerNew implements Runnable {
    MarketNew marketNew;

    ConsumerNew(MarketNew marketNew) {
        this.marketNew = marketNew;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            marketNew.getBread();
        }
    }
}