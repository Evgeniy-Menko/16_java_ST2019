package by.menko.composite.service.comparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SymbolComparator implements Comparator<String> {

    private char letter;

    public SymbolComparator(char l) {
        letter = l;
    }

    int matchesCount(String s) {
        String a = String.valueOf(letter);

        char i = a.toUpperCase().charAt(0);
        int found = 0;

        for (char c : s.toCharArray())
            if (letter == c || i == c)
                ++found;

        return found;
    }


    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
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
    public int compare(String o1, String o2) {
        return matchesCount(o1) - matchesCount(o2);
    }



}
