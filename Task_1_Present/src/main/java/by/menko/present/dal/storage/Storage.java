package by.menko.present.dal.storage;

import by.menko.present.entity.Presents;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Evgeniy Menko
 */
public final class Storage {
    /**
     * Object class a Storage.
     */
    private static Storage storage;
    /**
     * storage.
     */
    private Map<String, Presents> storageMap = new HashMap<String, Presents>();

    private Storage() {

    }

    /**
     * Getter for map storage.
     *
     * @return storage.
     */
    public Map<String, Presents> getStorage() {
        return storageMap;
    }


    /**
     * Singleton.
     *
     * @return class object
     */
    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }
}
