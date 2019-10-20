package by.menko.composite.dal.storage;

import by.menko.composite.bean.Component;
import by.menko.composite.dal.exception.NotInitializationException;

public final class Storage {
    /**
     * Object class.
     */
    private static Storage storage;
    /**
     * Object component.
     */
    private Component component;

    /**
     * Private constructor.
     */
    private Storage() {

    }

    /**
     * Singleton.
     *
     * @return object matrix.
     */
    public static synchronized Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    /**
     * getter for component.
     *
     * @return component.
     *
     * @throws NotInitializationException .
     */
    public Component getComponent() throws NotInitializationException {
        if (component != null) {
            return component;
        } else {
            throw new NotInitializationException();
        }
    }

    /**
     * Add component.
     *
     * @param comp object.
     */
    public void add(final Component comp) {
        component = comp;
    }
}

