package by.menko.composite.controller.command;

import by.menko.composite.controller.Controller;
import by.menko.composite.service.ReadFileAndAddStorage;

import java.io.IOException;

public class ReadFileCommand implements Command {
    /**
     * Read file and added to the storage.
     * @param request .
     */
    @Override
    public void execute(final String request) {
        ReadFileAndAddStorage service = new ReadFileAndAddStorage();
        logger.debug(new Controller().getBundle()
                .getMessage("readFile"));

        try {
            String response = service.readAndAddToStorage();
            logger.debug(new Controller().getBundle().getMessage(response));
        } catch (IOException e) {
            logger.debug(new Controller().getBundle().getMessage("errorRead"));
            logger.info("Error reading file. ");
        }

    }
}
