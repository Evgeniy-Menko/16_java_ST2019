package by.epam.thread.helloworld.ex04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadToDisable implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(
            ThreadToDisable.class);

    private boolean isActive;

    void disable() {
        isActive = false;
    }

    ThreadToDisable() {
        isActive = true;
    }

    public void run() {

        LOGGER.debug(
                "Поток %s начал работу... %n",
                Thread.currentThread().getName());

        int counter = 1; // счетчик циклов

        while (isActive) {

            String message = "Цикл " + counter++;
            LOGGER.debug(message);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                LOGGER.error("Поток прерван");
                Thread.currentThread().interrupt();
            }
        }

        LOGGER.debug(
                "Поток %s завершил работу... %n",
                Thread.currentThread().getName());
    }
}
