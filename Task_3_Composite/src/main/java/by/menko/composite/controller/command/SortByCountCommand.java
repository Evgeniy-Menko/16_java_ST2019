package by.menko.composite.controller.command;

import by.menko.composite.controller.Controller;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;
import by.menko.composite.service.Service;
import by.menko.composite.service.ServiceFactory;


import java.io.IOException;

public class SortByCountCommand implements Command {
    /**
     * Sort by count word or sentence.
     *
     * @param request .
     */
    @Override
    public void execute(final String request) {
        Service service = ServiceFactory.getInstance().getSortByCount();
        try {
            String response = service.execute(request);
            if (!"".equals(response)) {
                logger.debug(response);
            } else {
                logger.debug(new Controller().getBundle()
                        .getMessage("emptySort"));
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
