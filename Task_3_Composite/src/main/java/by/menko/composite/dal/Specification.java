package by.menko.composite.dal;

import by.menko.composite.bean.Component;
import by.menko.composite.dal.exception.SortException;

public interface Specification {
    /**
     * Method for overried.
     *
     * @param component object.
     *
     * @return string result.
     *
     * @throws SortException .
     */
    String specified(Component component) throws SortException;
}
