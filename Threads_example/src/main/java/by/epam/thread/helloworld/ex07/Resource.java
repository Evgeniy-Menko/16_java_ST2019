package by.epam.thread.helloworld.ex07;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

class Resource {
    private static final Logger LOGGER = LogManager.getLogger(Resource.class);

    private FileWriter fileWriter;

    Resource(String path) throws IOException {
        fileWriter = new FileWriter(path);
    }

    synchronized void writing(String str, int i) {
        try {
            String message = str + i;
            fileWriter.append(message);
            LOGGER.debug(message);
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 50));
        } catch (IOException | InterruptedException eValue) {
            LOGGER.error(eValue);
            Thread.currentThread().interrupt();
        }
    }

    void close() {
        try {
            fileWriter.close();
        } catch (IOException eValue) {
            LOGGER.error(eValue);
        }
    }
}
