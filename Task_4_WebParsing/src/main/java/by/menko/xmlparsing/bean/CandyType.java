package by.menko.xmlparsing.bean;

public enum CandyType {
    /**
     * Type.
     */
    CARAMEL, IRIS, CHOCOLATE;
    /**
     * Filling.
     */
    private boolean filling;


    /**
     * Get name to lower case.
     *
     * @return the name.
     */
    public String getName() {
        return this.toString().toLowerCase();
    }

    /**
     * Getter {@link CandyType#filling}.
     *
     * @return the filling.
     */
    public String getFilling() {
        if (filling) {
            return "YES";
        } else {
            return "NO";
        }
    }

    /**
     * Settre {@link CandyType#filling}.
     *
     * @param filling the filling to set.
     */
    public void setFilling(final boolean filling) {
        this.filling = filling;
    }

}
