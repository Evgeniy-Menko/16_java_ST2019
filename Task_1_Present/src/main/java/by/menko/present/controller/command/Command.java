package by.menko.present.controller.command;

import by.menko.present.dal.dao.Dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author Evgeniy Menko
 */
public interface Command {
    /**
     * Logger.(log4j2).
     */
    Logger LOG = LogManager.getLogger();
    /**
     * Object class dao.
     */
    Dao DAO = new Dao();

    /**
     * method for override.
     */
    void execute();
}
