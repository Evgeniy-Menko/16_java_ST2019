package by.menko.composite.bean;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


import java.util.List;

public interface Component {

    String operation();

    default void add(Component c) {
        throw new NotImplementedException();
    }

    default List<Component> getList() {
        return (List<Component>) new NotImplementedException();
    }

    default CompositeType getType() {
        throw new NotImplementedException();
    }

}
