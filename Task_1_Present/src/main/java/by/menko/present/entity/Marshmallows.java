package by.menko.present.entity;


import java.util.Objects;

/**
 * @author Evgeniy Menko
 */
public class Marshmallows extends Sweets {
    /**
     * Type marshmallows.
     */
    static final String TYPE = "marshmallows";
    /**
     * marshmallows's flavor.
     */
    private String flavor;

    /**
     * Constructor chocolate.
     *
     * @param name    name.
     * @param weight  weight.
     * @param sugar   sugar.
     * @param cost    cost.
     * @param flavors flavors.
     */
    public Marshmallows(final String name, final int weight,
                        final int sugar, final int cost, final String flavors) {
        super(TYPE, name, weight, sugar, cost);
        this.flavor = flavors;
    }

    /**
     * Getter.
     *
     * @return flavor.
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Setter.
     *
     * @param flavors .
     */
    public void setFlavor(final String flavors) {
        this.flavor = flavors;
    }

    /**
     * Equals.
     *
     * @param o Object.
     *
     * @return true or false.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Marshmallows that = (Marshmallows) o;
        return Objects.equals(flavor, that.flavor);
    }

    /**
     * HashCode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), flavor);
    }

    /**
     * toString.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return super.toString() + "," + flavor + "\n";
    }

    /**
     * Print Object.
     *
     * @return String.
     */
    @Override
    public String print() {
        return super.print() + ", flavor= " + flavor;
    }
}
