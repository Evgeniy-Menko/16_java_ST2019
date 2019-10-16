package by.menko.composite.service.comparator;


import by.menko.composite.bean.Component;

import java.util.Comparator;
import java.util.List;

public class LengthComparator implements Comparator<Component> {


    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.*
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     *
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     *
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(Component o1, Component o2) {
        return o1.getList().size() - o2.getList().size();
    }

}

