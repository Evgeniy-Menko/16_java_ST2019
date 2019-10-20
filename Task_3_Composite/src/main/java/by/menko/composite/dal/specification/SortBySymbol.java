package by.menko.composite.dal.specification;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.CompositeType;
import by.menko.composite.dal.Specification;
import by.menko.composite.dal.exception.SortException;
import by.menko.composite.service.comparator.AlphabeticalComparator;
import by.menko.composite.service.comparator.SymbolComparator;
import by.menko.composite.service.search.SearchByType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class SortBySymbol implements Specification {
    /**
     * Char symbol for sorting.
     */
    private char symbol;

    /**
     * Construcor.
     *
     * @param character symbol.
     */
    public SortBySymbol(final String character) {
        this.symbol = character.charAt(0);
    }

    /**
     * Sort text by symbol comparator and alphabetical comparator.
     *
     * @param component text.
     *
     * @return string.
     *
     * @throws SortException .
     */
    @Override
    public String specified(final Component component) throws SortException {
        List<Component> tokenList = new SearchByType()
                .search(component, CompositeType.TOKEN);
        StringBuilder sb = new StringBuilder();

        for (Component c : tokenList) {
            sb.append(" ").append(c.collect());
        }
        try {
            List<String> sorted = Arrays.stream(sb.toString().split("\\s"))
                    .sorted(new SymbolComparator(symbol)
                            .reversed()
                            .thenComparing(new AlphabeticalComparator()))
                    .collect(Collectors.toList());
            StringBuilder result = new StringBuilder();

            for (String s : sorted) {
                result.append(s).append(" ");
            }
            return result.toString();
        } catch (Exception e) {
            throw new SortException();
        }
    }
}
