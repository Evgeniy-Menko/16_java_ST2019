package by.menko.composite.dal.specification;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.CompositeType;
import by.menko.composite.dal.Specification;
import by.menko.composite.dal.exception.SortException;
import by.menko.composite.service.comparator.LengthComparator;
import by.menko.composite.service.search.SearchByType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortByCountWord implements Specification {
    /**
     * Sort text by count word.
     *
     * @param component text.
     *
     * @return string.
     *
     * @throws SortException .
     */
    @Override
    public String specified(final Component component) throws SortException {
        List<Component> sentenceList = new SearchByType()
                .search(component, CompositeType.SENTENCE);

        List<Component> sortedList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try {
            for (Component comp : sentenceList) {
                sortedList = comp.getAllChild().stream()
                        .flatMap(c -> c.getAllChild().stream())
                        .sorted(new LengthComparator())
                        .collect(Collectors.toList());

                for (Component b : sortedList) {
                    sb.append(" ").append(b.collect());
                }
                sb.append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            throw new SortException();
        }
    }
}
