package by.menko.composite.controller.command;

import by.menko.composite.controller.Controller;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;
import by.menko.composite.service.Service;
import by.menko.composite.service.ServiceFactory;


import java.io.IOException;

public class SortBySymbolCommand implements Command {
    /**
     * Sort by symbol.
     *
     * @param request .
     */
    @Override
    public void execute(final String request) {
        Service service = ServiceFactory.getInstance().getSortBySymbolService();
        logger.debug(new Controller().getBundle().getMessage("character"));
        String letter = new Controller().getScan().nextLine();
        try {
            String response = service.execute(letter);
            if (!"incorrectValue".equals(response)) {
                logger.debug(response);
            } else if ("".equals(response)) {
                logger.debug(new Controller().getBundle()
                        .getMessage("emptySort"));
            } else {
                logger.debug(new Controller().getBundle()
                        .getMessage("incorrectValue"));
            }
        } catch (SortException e) {
            logger.debug(new Controller().getBundle().getMessage("errorSort"));
            logger.info("Sort error.");
        } catch (NotInitializationException | IOException e) {
            logger.debug(new Controller().getBundle().getMessage("notIntil"));
            logger.info("Access error: Not initialization storage.");
        }
    }
}
