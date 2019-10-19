package by.menko.composite.bean;


import java.util.ArrayList;

public class Composite implements Component {

    private CompositeType type;

    private ArrayList<Component> components = new ArrayList<Component>();

    public CompositeType getType() {
        return type;
    }

    public ArrayList<Component> getList() {
        return components;
    }

    public Composite(final CompositeType compositeType) {
        this.type = compositeType;
    }

    @Override
    public String operation() {
        StringBuilder result = new StringBuilder();
        for (Component c : components) {
            switch (type) {

                case TEXT:
                    result.append("   ").append(c.operation()).append("\n");
                    break;
                case SENTENCE:
                    result.append(" ").append(c.operation());
                    break;
                case PARAGRAPH:
                case TOKEN:
                case WORD:
                    result.append(c.operation());
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
        return result.toString();
    }

    @Override
    public void add(final Component c) {
        components.add(c);
    }


}
