package by.menko.composite.controller.command;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public interface Command {
    /**
     * Object log4j2.
     */
    Logger logger = LogManager.getLogger();

    /**
     * method for override.
     *
     * @param request .
     */
    void execute(String request);
}
