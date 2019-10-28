package by.menko.xmlparsing.bean;

public class Unit {
    /**
     * Name.
     */
    private String name;
    /**
     * Count.
     */
    private double count;
    /**
     * Ci.
     */
    private String ci;

    /**
     * Getter {@link Unit#ci}.
     *
     * @return the ci.
     */
    public final String getCi() {
        return ci;
    }

    /**
     * Setter {@link Unit#ci}.
     *
     * @param ci the ci to set.
     */
    public final void setCi(final String ci) {
        this.ci = ci;
    }

    /**
     * Getter {@link Unit#name}.
     *
     * @return the name.
     */
    public final String getName() {
        return name;
    }

    /**
     * Setter {@link Unit#name}.
     *
     * @param name the nameIngredient to set.
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter {@link Unit#count}.
     *
     * @return the count.
     */
    public final double getCount() {
        return count;
    }

    public final void setCount(double count) {
        this.count = count;
    }
}
