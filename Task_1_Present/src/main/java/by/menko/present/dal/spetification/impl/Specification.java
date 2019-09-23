package by.menko.present.dal.spetification.impl;

import by.menko.present.entity.Presents;
import by.menko.present.entity.Sweets;

import java.util.List;
import java.util.Map;

/**
 * @author Evgeniy Menko
 */
public interface Specification {
    /**
     * Overried method.
     *
     * @param storage .
     *
     * @return list<Sweets>.
     */
    List<Sweets> specified(Map<String, Presents> storage);
}
