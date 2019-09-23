package by.menko.present.entity;


import java.util.Objects;

/**
 * @author Evgeniy Menko
 */
public class Chocolate extends Sweets {
    /**
     * Type chocolate.
     */
    static final String TYPE = "chocolate";
    /**
     * Filling chocolate.
     */
    private String filling;

    /**
     * Constructor chocolate.
     *
     * @param name   name.
     * @param weight weight.
     * @param sugar  sugar.
     * @param cost   cost.
     * @param fillin filling.
     */
    public Chocolate(final String name, final int weight,
                     final int sugar, final int cost, final String fillin) {
        super(TYPE, name, weight, sugar, cost);
        this.filling = fillin;
    }

    /**
     * Getter.
     *
     * @return filling.
     */
    public String getFilling() {
        return filling;
    }

    /**
     * Setter.
     *
     * @param fillingChocolate .
     */
    public void setFilling(final String fillingChocolate) {
        this.filling = fillingChocolate;
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
        Chocolate chocolate = (Chocolate) o;
        return Objects.equals(filling, chocolate.filling);
    }

    /**
     * HashCode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), filling);
    }

    /**
     * toString.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return super.toString() + "," + filling + "\n";
    }

    /**
     * Print object.
     *
     * @return String.
     */
    @Override
    public String print() {
        return super.print() + ", filling= " + filling;
    }
}
