package by.menko.composite.controller.command;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public interface Command {

    Logger logger = LogManager.getLogger();

    /**
     * method for override.
     *
     * @param action .
     */
    void execute(final String request);
}
