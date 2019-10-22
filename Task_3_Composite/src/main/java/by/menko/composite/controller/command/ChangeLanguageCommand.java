package by.menko.composite.controller.command;

import by.menko.composite.controller.Controller;

import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;
import by.menko.composite.service.Service;
import by.menko.composite.service.ServiceFactory;

import java.io.IOException;

public class ChangeLanguageCommand implements Command {
    /**
     * Change language command.
     *
     * @param request .
     */
    @Override
    public void execute(final String request) {
        Service service = ServiceFactory.getInstance().getChooseLanguage();

        logger.debug(new Controller().getBundle().getMessage("chooseLanguage"));
        String language = new Controller().getScan().nextLine();
        String response = null;
        try {
            response = service.execute(language);
            logger.debug(new Controller().getBundle().getMessage(response));
        } catch (IOException | SortException | NotInitializationException e) {
            logger.debug(new Controller().getBundle().getMessage("notIntil"));
            logger.info("Access error: Not initialization storage.");
        }

    }
}
