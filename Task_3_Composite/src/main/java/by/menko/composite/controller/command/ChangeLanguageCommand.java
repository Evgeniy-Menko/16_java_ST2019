package by.menko.composite.controller.command;

import by.menko.composite.controller.Controller;
import by.menko.composite.service.ChooseLanguage;

public class ChangeLanguageCommand implements Command {
    /**
     * Change language command.
     *
     * @param request .
     */
    @Override
    public void execute(final String request) {
        ChooseLanguage service = new ChooseLanguage();
        logger.debug(new Controller().getBundle().getMessage("chooseLanguage"));
        String language = new Controller().getScan().nextLine();
        String response = service.changeLanguage(language);
        logger.debug(new Controller().getBundle().getMessage(response));
    }
}
