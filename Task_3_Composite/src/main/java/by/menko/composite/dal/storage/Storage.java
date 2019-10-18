package by.menko.composite.dal.storage;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.Composite;
import by.menko.composite.bean.CompositeType;
import by.menko.composite.dal.exception.NotInitializationException;

public class Storage {
    /**
     * Object class.
     */
    private static Storage storage;

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

    public Component getComponent() throws NotInitializationException {
        if (component != null) {
            return component;
        } else {
            throw new NotInitializationException();
        }
    }

    public void add(Component comp) {
        component = comp;
    }
}

