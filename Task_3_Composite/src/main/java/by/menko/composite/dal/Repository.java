package by.menko.composite.dal;

import by.menko.composite.bean.Component;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;

public interface Repository {
    void addComponent(Component component);

    Component getComponent() throws NotInitializationException;

    String query(Specification specification)
            throws NotInitializationException, SortException;
}
