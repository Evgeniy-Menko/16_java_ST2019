package by.epam.thread.helloworld.ex06;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class ProducerConsumerApp {

    private static final Logger LOGGER =
            LogManager.getLogger(ProducerConsumerApp.class);

    public static void main(String[] args) {

        Store store = new Store();
        Producer producer1 = new Producer("Producer #1", store);
        producer1.start();
        Producer producer2 = new Producer("Producer #2", store);
        producer2.start();
        Consumer consumer1 = new Consumer("Consumer #1", store);
        consumer1.start();
        Consumer consumer2 = new Consumer("Consumer #2", store);
        consumer2.start();

        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }

        LOGGER.debug("Рабочий день завершен! Приходите завтра!");
    }
}

class Store {

    private static final Logger LOGGER = LogManager.getLogger(Store.class);

    private int product = 0;

    synchronized void put() {
        while (product >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                LOGGER.error(e);
                Thread.currentThread().interrupt();
            }
        }
        product++;
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
        String message = String.format("%s добавил 1 товар.%nТоваров на " +
                "складе: %d", Thread
                .currentThread()
                .getName(), product);
        LOGGER.debug(message);
        notifyAll();
    }

    synchronized void get() {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                LOGGER.error(e);
                Thread.currentThread().interrupt();
            }
        }
        product--;
        String message = String.format("%s купил 1 товар.%nТоваров на " +
                "складе: %d", Thread
                .currentThread()
                .getName(), product);
        LOGGER.debug(message);
        notifyAll();
    }
}

class Producer extends Thread {

    private Store store;

    Producer(final String nameValue, final Store storeValue) {
        this.setName(nameValue);
        store = storeValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            store.put();
        }
    }
}

class Consumer extends Thread {

    private Store store;

    Consumer(final String nameValue, final Store storeValue) {
        this.setName(nameValue);
        store = storeValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            store.get();
        }
    }
}
