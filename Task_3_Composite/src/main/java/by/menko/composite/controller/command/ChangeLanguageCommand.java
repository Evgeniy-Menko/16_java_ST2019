package by.menko.composite.controller.command;

import by.menko.composite.controller.Controller;
import by.menko.composite.service.ChooseLanguage;

public class ChangeLanguageCommand implements Command {
    @Override
    public void execute(final String request) {
        ChooseLanguage service = new ChooseLanguage();
        logger.debug(new Controller().getBundle().getMessage("chooseLanguage"));
        String response = service.changeLanguage();
        logger.debug(new Controller().getBundle().getMessage(response));
    }
}
