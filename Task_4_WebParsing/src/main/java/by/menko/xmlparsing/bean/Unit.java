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
     * Getter ci.
     *
     * @return the ci.
     */
    public final String getCi() {
        return ci;
    }

    /**
     * Setter ci.
     *
     * @param ciUnit the ci to set.
     */
    public final void setCi(final String ciUnit) {
        this.ci = ciUnit;
    }

    /**
     * Getter name.
     *
     * @return the name.
     */
    public final String getName() {
        return name;
    }

    /**
     * Setter name.
     *
     * @param nameUnit the nameIngredient to set.
     */
    public final void setName(final String nameUnit) {
        this.name = nameUnit;
    }

    /**
     * Getter count.
     *
     * @return the count.
     */
    public final double getCount() {
        return count;
    }

    /**
     * Setter count.
     *
     * @param countUnit .
     */
    public final void setCount(final double countUnit) {
        this.count = countUnit;
    }
}
