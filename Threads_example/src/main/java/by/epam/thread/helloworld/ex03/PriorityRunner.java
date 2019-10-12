package by.epam.thread.helloworld.ex03;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PriorityRunner {

    private static final Logger LOGGER = LogManager.getLogger(
            PriorityRunner.class);

    public static void main(String[] args) throws InterruptedException {

        LOGGER.debug("The Runner has started!");

        PriorThread min = new PriorThread("Min");
        PriorThread max = new PriorThread("Max");
        PriorThread norm = new PriorThread("Norm");

        min.setPriority(Thread.MIN_PRIORITY); // 1
        max.setPriority(Thread.MAX_PRIORITY); // 10
        norm.setPriority(Thread.NORM_PRIORITY); // 5

        min.start();
        norm.start();
        max.start();

        norm.join(100);

        LOGGER.debug("The Runner has stopped!");
    }

}

