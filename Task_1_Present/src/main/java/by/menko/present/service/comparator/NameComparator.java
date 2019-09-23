package by.menko.present.service.comparator;

import by.menko.present.entity.Sweets;

import java.util.Comparator;

/**
 * @author Evgeniy Menko
 */
public class NameComparator implements Comparator<Sweets> {
    /**
     * Comparator for name.
     *
     * @param o1 object sweet1.
     * @param o2 object sweet1.
     *
     * @return int -1 : o1 < o2, return 0 : o1 == o2, return +1 : o1 > o2
     */
    @Override
    public int compare(final Sweets o1, final Sweets o2) {
        return o1.getName().charAt(0) - o2.getName().charAt(0);
    }
}
