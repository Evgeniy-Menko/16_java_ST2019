package by.menko.composite.controller.command;

import by.menko.composite.controller.Controller;
import by.menko.composite.service.ReadFileAndAddStorage;

import java.io.IOException;

public class ReadFileCommand implements Command {
    @Override
    public void execute(String request) {
        ReadFileAndAddStorage read = new ReadFileAndAddStorage();
        logger.debug(new Controller().getBundle()
                .getMessage("readFile"));

        try {
            String key = read.readAndAddToStorage();
            logger.debug(new Controller().getBundle().getMessage(key));
        } catch (IOException e) {
            logger.debug(new Controller().getBundle().getMessage("errorRead"));
            logger.info(new Controller().getBundle().getMessage("errorRead"));
        }

    }
}
