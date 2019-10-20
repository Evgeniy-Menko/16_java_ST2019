package by.menko.composite.bean;


import java.util.List;

public interface Component {
    /**
     * Collect text.
     *
     * @return text.
     */
    String collect();

    /**
     * Add Component.
     *
     * @param c component.
     */
    default void add(Component c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get List with childs.
     *
     * @return list.
     */
    default List<Component> getAllChild() {
        throw new UnsupportedOperationException();
    }

    /**
     * getter for type.
     *
     * @return type.
     */
    default CompositeType getType() {
        throw new UnsupportedOperationException();
    }

}

