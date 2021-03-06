package by.menko.composite.dal.specification;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.CompositeType;
import by.menko.composite.dal.Specification;
import by.menko.composite.dal.exception.SortException;
import by.menko.composite.service.comparator.LengthComparator;
import by.menko.composite.service.search.SearchByType;

import java.util.List;
import java.util.stream.Collectors;

public class SortByCountSentence implements Specification {

    /**
     * Sort text by count sentence.
     *
     * @param component .
     *
     * @return String.
     *
     * @throws SortException .
     */
    @Override
    public String specified(final Component component) throws SortException {

        List<Component> paragraphList = new SearchByType()
                .search(component, CompositeType.PARAGRAPH);
        try {
            List<Component> sortedList = paragraphList.stream()
                    .sorted(new LengthComparator())
                    .collect(Collectors.toList());
            StringBuilder result = new StringBuilder();
            for (Component c : sortedList) {
                result.append(c.collect()).append("\n");

            }
            return result.toString();
        } catch (Exception e) {
            throw new SortException();
        }
    }
}
