package by.menko.composite.dal;

import by.menko.composite.bean.Component;
import by.menko.composite.dal.exception.SortException;

public interface Specification {
    String specified(Component component) throws SortException;
}
