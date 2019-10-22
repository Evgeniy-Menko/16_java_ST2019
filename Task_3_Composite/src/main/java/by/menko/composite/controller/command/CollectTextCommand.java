package by.menko.composite.controller.command;

import by.menko.composite.controller.Controller;
import by.menko.composite.dal.exception.NotInitializationException;

import by.menko.composite.service.Service;
import by.menko.composite.service.ServiceFactory;

import java.io.IOException;

public class CollectTextCommand implements Command {
    /**
     * Collect text and save to the file.
     *
     * @param request .
     */
    @Override
    public void execute(final String request) {
        Service service = ServiceFactory.getInstance().getCollectText();
        try {
            String response = service.execute();
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
