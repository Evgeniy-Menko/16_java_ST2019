package by.menko.composite.dal;

import by.menko.composite.bean.Component;

public interface Repository {
    void addComponent(Component component);

    Component getComponent();

    String query(Specification specification);
}
