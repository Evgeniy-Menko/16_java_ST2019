package by.epam.thread.helloworld.ex10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class Runner {

    private Runner() {
    }

    public static void main(final String[] args) {

        Semaphore sem = new Semaphore(3); // 1 разрешение
        CommonResource res = new CommonResource();
        new Thread(new CountThread(res, sem, "CountThread 1")).start();
        new Thread(new CountThread(res, sem, "CountThread 2")).start();
        new Thread(new CountThread(res, sem, "CountThread 3")).start();
    }
}

class CommonResource {
    private int x = 0;

    /**
     * Sets x.
     *
     * @param x value of x.
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Gets x.
     *
     * @return value of x.
     */
    public int getX() {
        return x;
    }

    void increment() {
        x++;
    }
}

class CountThread implements Runnable {

    private static final Logger LOGGER =
            LogManager.getLogger(CountThread.class);

    private CommonResource res;
    private Semaphore sem;
    private String name;

    CountThread(final CommonResource res, final Semaphore sem,
                final String name) {
        this.res = res;
        this.sem = sem;
        this.name = name;
    }

    public void run() {
        try {
            String message1 = name + " ожидает разрешение";
            LOGGER.debug(message1);
            sem.acquire();
            res.setX(1);
            for (int i = 1; i < 5; i++) {
                String message2 = this.name + ": " + res.getX();
                LOGGER.debug(message2);
                res.increment();
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
        String message3 = name + " освобождает разрешение";
        LOGGER.debug(message3);
        sem.release();
    }
}
