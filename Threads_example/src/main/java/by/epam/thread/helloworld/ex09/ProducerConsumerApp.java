package by.epam.thread.helloworld.ex09;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class ProducerConsumerApp {

    private static final Logger LOGGER =
            LogManager.getLogger(ProducerConsumerApp.class);

    private ProducerConsumerApp() {
    }

    public static void main(final String[] args) {

        Store store = new Store();
        Producer producer1 = new Producer(store, 5);
        Producer producer2 = new Producer(store, 7);
        Consumer consumer1 = new Consumer(store, 3);
        Consumer consumer2 = new Consumer(store, 4);
        Consumer consumer3 = new Consumer(store, 5);

        Thread t1 = new Thread(producer1, "Producer #1");
        t1.start();
        Thread t2 = new Thread(producer2, "Producer #2");
        t2.start();
        Thread t3 = new Thread(consumer1, "Consumer #1");
        t3.start();
        Thread t4 = new Thread(consumer2, "Consumer #2");
        t4.start();
        Thread t5 = new Thread(consumer3, "Consumer #3");
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }

        LOGGER.debug("Оставшиеся товары на складе: "
                + store.getProduct());
    }
}

class Store {

    private static final Logger LOGGER = LogManager.getLogger(Store.class);

    private int product = 0;
    private ReentrantLock locker;
    private Condition condition;

    Store() {
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    void get() {

        locker.lock();
        try {
            while (product < 1) {
                condition.await();
            }

            product--;
            String message1 = String.format("%s купил 1 товар",
                    Thread.currentThread()
                                                                      .getName());
            LOGGER.debug(message1);
            String message2 = String.format("Товаров на складе: %d", product);

            LOGGER.debug(message2);

            // сигнализируем
            condition.signalAll();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            locker.unlock();
        }
    }

    void put() {

        locker.lock();
        try {
            while (product >= 3) {
                condition.await();
            }

            product++;

            TimeUnit.MILLISECONDS.sleep(100);

            String message1 = String.format("%s добавил 1 товар",
                    Thread.currentThread()
                                                                        .getName());
            LOGGER.debug(message1);
            String message2 = String.format("Товаров на складе: %d", product);
            LOGGER.debug(message2);
            // сигнализируем
            condition.signalAll();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            locker.unlock();
        }
    }

    /**
     * Gets product.
     *
     * @return value of product.
     */
    public int getProduct() {
        return product;
    }
}

//класс Производитель
class Producer implements Runnable {

    private Store store;
    private int product;

    Producer(final Store storeValue, final int productValue) {
        store = storeValue;
        product = productValue;
    }

    public void run() {
        for (int i = 1; i < product; i++) {
            store.put();
        }
    }
}

//Класс Потребитель
class Consumer implements Runnable {

    private Store store;
    private int product;

    Consumer(final Store storeValue, final int productValue) {
        store = storeValue;
        product = productValue;
    }

    public void run() {
        for (int i = 1; i <= product; i++) {
            store.get();
        }
    }
}
