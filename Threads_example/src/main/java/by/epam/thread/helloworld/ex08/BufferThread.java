package by.epam.thread.helloworld.ex08;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class BufferThread {

    private static final Logger LOGGER = LogManager.getLogger(BufferThread.class);

    private static int counter = 0;
    private static final StringBuilder s = new StringBuilder();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (s) {
                while (BufferThread.counter++ < 3) {
                    s.append("A");
                    String message = "> " + counter + " ";
                    LOGGER.debug(message);
                    LOGGER.debug(s);
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException eValue) {
                        LOGGER.error(eValue);
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException eValue) {
            LOGGER.error(eValue);
            Thread.currentThread().interrupt();
        }
        while (BufferThread.counter++ < 6) {
            String message = "< " + counter + " ";
            LOGGER.debug(message);
            BufferThread.s.append("B");
            LOGGER.debug(s);
        }
    }
}
