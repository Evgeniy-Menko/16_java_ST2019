package by.menko.xmlparsing.dal;

public enum XmlEnum {
    /**
     * Elements names.
     */
    CANDIES("candies"), CANDY("candy"), NAME("name"),
    /**
     * Elements names.
     */
    ENERGY("energy"), TYPE("type"),
    /**
     * Elements names.
     */
    WATER("water"), SUGAR("sugar"), FRUCTOSE("fructose"), TASTE("taste"),
    /**
     * Elements names.
     */
    CHOCOLATE_TYPE("chocolateType"), VANILIN("vanillin"),
    /**
     * Elements names.
     */

    PROTEINS("proteins"), FATS("fats"),
    /**
     * Elements names.
     */
    CARBOHYDRATES("carbohydrates"), PRODUCTION("production"),
    /**
     * Elements names.
     */
    INGREDIENTS("ingredients"), VALUE("value"),
    /**
     * Elements names.
     */
    PRODUCTION_DATE("productionDate"),
    /**
     * Attribute name.
     */
    ID("id"), CI("ci"), FILLING("filling");

    /**
     * the value.
     */
    private String value;

    /**
     * Constructor with parameters.
     *
     * @param values - the constant value.
     */
    XmlEnum(final String values) {
        this.value = values;
    }


    /**
     * Get value.
     *
     * @return the value.
     */
    public String value() {
        return value;
    }

}
