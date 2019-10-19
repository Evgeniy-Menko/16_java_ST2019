package by.menko.composite.dal.repository;

import by.menko.composite.bean.Component;
import by.menko.composite.dal.Repository;
import by.menko.composite.dal.Specification;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;
import by.menko.composite.dal.storage.Storage;

public class StorageRepository implements Repository {

    private Storage storage = Storage.getInstance();

    @Override
    public void addComponent(final Component component) {
        storage.add(component);
    }

    @Override
    public Component getComponent() throws NotInitializationException {

        return storage.getComponent();
    }

    @Override
    public String query(final Specification specification)
            throws NotInitializationException, SortException {
        return specification.specified(getComponent());
    }
}
