package by.menko.composite.service;

import by.menko.composite.dal.Repository;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;
import by.menko.composite.dal.repository.StorageRepository;
import by.menko.composite.dal.specification.SortByCountSentence;
import by.menko.composite.dal.specification.SortByCountWord;

public class SortByCount implements Service {
    /**
     * Sort by count word or sentence.
     *
     * @param request param for type sorting.
     *
     * @return String result.
     *
     * @throws SortException              .
     * @throws NotInitializationException .
     */
    public String execute(final String request)
            throws SortException, NotInitializationException {
        Repository repository = new StorageRepository();
        String response;
        if ("2".equals(request)) {
            response = repository.query(new SortByCountSentence());
        } else {
            response = repository.query(new SortByCountWord());
        }
        return response;
    }
}
