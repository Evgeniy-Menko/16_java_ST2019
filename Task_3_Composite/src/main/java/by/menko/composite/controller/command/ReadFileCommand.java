package by.menko.composite.controller.command;

import by.menko.composite.controller.Controller;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;

import by.menko.composite.service.Service;
import by.menko.composite.service.ServiceFactory;

import java.io.IOException;

public class ReadFileCommand implements Command {
    /**
     * Read file and added to the storage.
     *
     * @param request .
     */
    @Override
    public void execute(final String request) {
        Service service = ServiceFactory.getInstance()
                .getReadFileAndAddStorage();
        logger.debug(new Controller().getBundle()
                .getMessage("readFile"));
        String nameFile = new Controller().getScan().nextLine();
        try {
            String response = service.execute(nameFile);
            logger.debug(new Controller().getBundle().getMessage(response));
        } catch (IOException | SortException | NotInitializationException e) {
            logger.debug(new Controller().getBundle().getMessage("errorRead"));
            logger.info("Error reading file. ");
        }

    }
}
