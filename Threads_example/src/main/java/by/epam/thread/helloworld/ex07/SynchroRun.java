package by.epam.thread.helloworld.ex07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class SynchroRun {
    private static final Logger LOGGER = LogManager.getLogger(SynchroRun.class);

    public static void main(String[] args) {
        Resource resource = null;

        try {
            resource = new Resource("data\\result.txt");
            SyncThread t1 = new SyncThread("First", resource);
            SyncThread t2 = new SyncThread("Second", resource);

            t1.start();
            t2.start();

            t1.join();
            t2.join();
        } catch (IOException | InterruptedException eValue) {
            LOGGER.error(eValue);
            Thread.currentThread().interrupt();
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
    }
}
