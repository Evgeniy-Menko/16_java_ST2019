package by.menko.present.dal.spetification;

import by.menko.present.dal.spetification.impl.Specification;
import by.menko.present.entity.Presents;
import by.menko.present.entity.Sweets;
import by.menko.present.service.comparator.NameComparator;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Evgeniy Menko
 */
public class SortByNameSpecification implements Specification {
    /**
     * Sort sweets by name.
     *
     * @param storage .
     *
     * @return list<Sweets>.
     */
    @Override
    public List<Sweets> specified(final Map<String, Presents> storage) {
        return storage.values().stream()
                .flatMap(c -> c.getSweets().values().stream())
                .sorted(new NameComparator())
                .collect(Collectors.toList());
    }
}
