package by.menko.composite.bean;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface Component {
    void operation();

    default void add(Component c) {
        throw new NotImplementedException();
    }


    default void remove(Component c) {
        throw new NotImplementedException();
    }

    default Component getChild(int index) {
        return (Component) new NotImplementedException();
    }
}
