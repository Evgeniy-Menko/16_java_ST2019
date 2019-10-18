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

    @Override
    public String specified(Component component) throws SortException {
        List<Component> sentenceList = new SearchByType()
                .search(component, CompositeType.SENTENCE);

        List<Component> sortedList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        try {
            for (Component comp : sentenceList) {
                sortedList = comp.getList().stream()
                        .flatMap(c -> c.getList().stream())
                        .sorted(new LengthComparator())
                        .collect(Collectors.toList());

                for (Component b : sortedList) {
                    sb.append(" ").append(b.operation());
                }
                sb.append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            throw new SortException();
        }
    }
}
