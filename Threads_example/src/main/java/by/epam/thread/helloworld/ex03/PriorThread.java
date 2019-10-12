package by.epam.thread.helloworld.ex03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class PriorThread extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(
            PriorThread.class);

    PriorThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 50; i++) {

            String message = getName() + " " + i;
            LOGGER.debug(message);

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                LOGGER.error(e);
                Thread.currentThread().interrupt();
            }
        }
    }
}

