package by.menko.composite.service;

import by.menko.composite.controller.Controller;
import by.menko.composite.dal.Repository;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;
import by.menko.composite.dal.repository.StorageRepository;
import by.menko.composite.dal.specification.SortBySymbol;
import by.menko.composite.service.validator.Validator;

public class SortBySymbolService {

    public String sortBySymbol() throws SortException,
            NotInitializationException {
        Repository repository = new StorageRepository();
        String character = new Controller().getScan().nextLine();
        if (new Validator().validateCharI(character)) {
            return repository.query(new SortBySymbol(character));
        } else {
            return "incorrectValue";
        }
    }
}
