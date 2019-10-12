package by.epam.thread.helloworld.ex04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunnerThreadToDisable {

    private static final Logger LOGGER = LogManager.getLogger(
            RunnerThreadToDisable.class);

    public static void main(String[] args) {

        LOGGER.debug("Главный поток начал работу...");

        ThreadToDisable myThread = new ThreadToDisable();
        Thread myT = new Thread(myThread, "name of ThreadToDisable");
        myT.start();

        try {
            Thread.sleep(1100);

            myThread.disable();

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("Поток прерван");
            Thread.currentThread().interrupt();
        }

        LOGGER.debug("Главный поток завершил работу...");
    }
}
