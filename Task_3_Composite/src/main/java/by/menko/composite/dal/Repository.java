package by.menko.composite.dal;

import by.menko.composite.bean.Component;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;

public interface Repository {
    /**
     * Add component to the storage.
     *
     * @param component object.
     */
    void addComponent(Component component);

    /**
     * getComponent.
     *
     * @return component.
     *
     * @throws NotInitializationException .
     */
    Component getComponent() throws NotInitializationException;

    /**
     * Method for Specification.
     *
     * @param specification object.
     *
     * @return result string.
     *
     * @throws NotInitializationException .
     * @throws SortException              .
     */
    String query(Specification specification)
            throws NotInitializationException, SortException;
}
