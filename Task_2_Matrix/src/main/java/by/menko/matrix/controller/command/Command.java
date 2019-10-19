package by.menko.matrix.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Command {
    /**
     * method for override.
     *
     * @param action .
     */
    void execute(String action);
}
