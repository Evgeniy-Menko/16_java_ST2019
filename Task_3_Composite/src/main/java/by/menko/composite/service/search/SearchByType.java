package by.menko.composite.service.search;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.CompositeType;

import java.util.ArrayList;
import java.util.List;

public class SearchByType {
    /**
     * List component.
     */
    private List<Component> components = new ArrayList<>();

    /**
     * Search component by type.
     *
     * @param component component.
     * @param type      type for search.
     *
     * @return list components.
     */
    public List<Component> search(final Component component,
                                  final CompositeType type) {
        for (Component entity : component.getAllChild()) {
            if (entity.getType().equals(type)) {
                components.add(entity);
            } else if (!entity.getType().equals(CompositeType.SYMBOL)) {
                search(entity, type);
            }
        }
        return components;
    }
}
