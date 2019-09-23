package by.menko.present.dal.spetification;

import by.menko.present.entity.Presents;
import by.menko.present.entity.Sweets;
import by.menko.present.dal.spetification.impl.Specification;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Evgeniy Menko
 */
public class FindBySugarSpecification implements Specification {
    /**
     * Minimum sugar for find by sugar.
     */
    private int minSugar;
    /**
     * Maximum sugar for find by sugar.
     */
    private int maxSugar;

    /**
     * Constructor by two parametrs.
     *
     * @param miniSugar minimum sugar.
     * @param maxiSugar maximum sugar.
     */
    public FindBySugarSpecification(final int miniSugar, final int maxiSugar) {
        this.minSugar = miniSugar;
        this.maxSugar = maxiSugar;
    }

    /**
     * Find sweets by sugar range.
     *
     * @param storage .
     *
     * @return list<Sweets>.
     */
    @Override
    public List<Sweets> specified(final Map<String, Presents> storage) {
        return storage.values().stream()
                .flatMap(c -> c.getSweets().values().stream())
                .filter(x -> x.getSugar() >= minSugar
                        && x.getSugar() <= maxSugar)
                .collect(Collectors.toList());

    }
}
