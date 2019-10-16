package by.menko.composite.dal.specification;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.CompositeType;
import by.menko.composite.dal.Specification;
import by.menko.composite.service.comparator.LengthComparator;
import by.menko.composite.service.search.SearchByType;

import java.util.List;
import java.util.stream.Collectors;

public class SortByCountSentence implements Specification {


    @Override
    public String specified(Component component) {

        List<Component> paragraphList = new SearchByType().search(component, CompositeType.PARAGRAPH);

        List<Component> sortedList = paragraphList.stream().sorted(new LengthComparator()).collect(Collectors.toList());
        StringBuilder result = new StringBuilder();
        for (Component c : sortedList) {
            result.append(c.operation()).append("\n");

        }
        return result.toString();

    }
}
