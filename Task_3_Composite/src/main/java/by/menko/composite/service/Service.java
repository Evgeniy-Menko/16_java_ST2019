package by.menko.composite.service;

import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;

import java.io.IOException;

public interface Service {
    /**
     * default.
     *
     * @return result string.
     *
     * @throws NotInitializationException .
     * @throws IOException                .
     */
    default String execute() throws NotInitializationException, IOException {
        throw new UnsupportedOperationException();
    }

    /**
     * default.
     *
     * @param request .
     *
     * @return result string.
     *
     * @throws NotInitializationException .
     * @throws IOException   .
     * @throws SortException .
     */
    default String execute(String request) throws IOException,
            SortException, NotInitializationException {
        throw new UnsupportedOperationException();
    }
}
