package by.menko.composite.bean;


import java.util.List;

public interface Component {

    String operation();

    default void add(Component c) {
        throw new UnsupportedOperationException();
    }

    default List<Component> getList() {
        throw new UnsupportedOperationException();
    }

    default CompositeType getType() {
        throw new UnsupportedOperationException();
    }

}
