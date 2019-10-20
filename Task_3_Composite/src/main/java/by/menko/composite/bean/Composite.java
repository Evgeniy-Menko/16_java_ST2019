package by.menko.composite.bean;


import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    /**
     * Type object.
     */
    private CompositeType type;
    /**
     * Array list with childs.
     */
    private ArrayList<Component> components = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param compositeType type object.
     */
    public Composite(final CompositeType compositeType) {
        this.type = compositeType;
    }

    /**
     * getter for Type.
     *
     * @return type object.
     */
    @Override
    public CompositeType getType() {
        return type;
    }

    /**
     * get List with all childs.
     *
     * @return arraylist.
     */
    @Override
    public List<Component> getAllChild() {
        return components;
    }

    /**
     * Collect text.
     *
     * @return text.
     */
    @Override
    public String collect() {
        StringBuilder result = new StringBuilder();
        for (Component c : components) {
            switch (type) {

                case TEXT:
                    result.append("   ").append(c.collect()).append("\n");
                    break;
                case SENTENCE:
                    result.append(" ").append(c.collect());
                    break;
                case PARAGRAPH:
                case TOKEN:
                case WORD:
                    result.append(c.collect());
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
        return result.toString();
    }

    /**
     * Add component.
     *
     * @param component .
     */
    @Override
    public void add(final Component component) {
        components.add(component);
    }


}
