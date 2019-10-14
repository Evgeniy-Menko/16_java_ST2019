package by.menko.composite.bean;

import java.util.ArrayList;
import java.util.Objects;

public class Composite implements Component {
    private CompositeType type;
    private ArrayList<Component> components = new ArrayList<Component>();

    public ArrayList<Component> getComponents() {
        return components;
    }

    public Composite(final CompositeType compositeType) {
        this.type = compositeType;
    }

    @Override
    public void operation() {

    }

    @Override
    public void add(Component c) {
        components.add(c);

    }

    @Override
    public void remove(Component c) {
        components.remove(c);
    }

    @Override
    public Component getChild(int index) {
       return components.get(index);
    }

}
