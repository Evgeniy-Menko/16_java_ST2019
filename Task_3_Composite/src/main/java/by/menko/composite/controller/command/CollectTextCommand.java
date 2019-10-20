package by.menko.composite.controller.command;

import by.menko.composite.controller.Controller;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.service.CollectText;

import java.io.IOException;

public class CollectTextCommand implements Command {
    /**
     * Collect text and save to the file.
     * @param request .
     */
    @Override
    public void execute(final String request) {
        CollectText service = new CollectText();
        try {
            String response = service.collect();
            logger.debug(response);
        } catch (NotInitializationException e) {
            logger.debug(new Controller().getBundle().getMessage("notIntil"));
            logger.info("Access error: Not initialization storage.");
        } catch (IOException e) {
            logger.debug(new Controller().getBundle().getMessage("notIntil"));
            logger.info("Error writing to file.");
        }
    }
}
