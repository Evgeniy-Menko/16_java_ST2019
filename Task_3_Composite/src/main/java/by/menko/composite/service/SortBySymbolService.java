package by.menko.composite.service;


import by.menko.composite.dal.Repository;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;
import by.menko.composite.dal.repository.StorageRepository;
import by.menko.composite.dal.specification.SortBySymbol;
import by.menko.composite.service.validator.Validator;

public class SortBySymbolService implements Service {
    /**
     * Sort by symbol.
     *
     * @param letter .
     *
     * @return string result.
     *
     * @throws SortException              .
     * @throws NotInitializationException .
     */
    public String execute(final String letter) throws SortException,
            NotInitializationException {
        Repository repository = new StorageRepository();

        if (new Validator().validateCharI(letter)) {
            return repository.query(new SortBySymbol(letter));
        } else {
            return "incorrectValue";
        }
    }
}
