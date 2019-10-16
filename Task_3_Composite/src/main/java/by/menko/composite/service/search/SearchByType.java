package by.menko.composite.service.search;

import by.menko.composite.bean.Component;
import by.menko.composite.bean.CompositeType;

import java.util.ArrayList;
import java.util.List;

public class SearchByType {
    private List<Component> components = new ArrayList<>();

    public List<Component> search(Component component, CompositeType type) {
        for (Component entity : component.getList()) {
            if (entity.getType().equals(type)) {
                components.add(entity);
            } else if (!entity.getType().equals(CompositeType.SYMBOL)) {
                search(entity, type);
            }
        }
        return components;
    }
}
