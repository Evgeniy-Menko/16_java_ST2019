package by.menko.xmlparsing.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    /**
     * Object log4j2.
     */
    Logger logger = LogManager.getLogger();


    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
