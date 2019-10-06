package by.menko.matrix.controller.command;

import by.menko.matrix.service.ReadFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ReadFileCommand implements Command {
    /**
     * log4j2.
     */
    private Logger logger = LogManager.getLogger();

    /**
     * Calls the service read file.
     *
     * @param action .
     */

    @Override
    public void execute(final String action) {
        ReadFile readFile = new ReadFile();
        try {
            String response = readFile.readFileAndAdd();
            logger.info(response);
        } catch (IOException e) {
            logger.info("File not found.");
        }
    }
}
