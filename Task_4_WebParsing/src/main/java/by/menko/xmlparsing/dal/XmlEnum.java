package by.menko.xmlparsing.dal;

public enum XmlEnum {
    /**
     * Elements names.
     */
    CANDIES("candies"), CANDY("candy"), NAME("name"), ENERGY("energy"), TYPE("type"),
    /**
     * Elements names.
     */
    WATER("water"), SUGAR("sugar"), FRUCTOSE("fructose"), TASTE("taste"),
    /**
     * Elements names.
     */
    CHOCOLATE_TYPE("chocolateType"), VANILIN("vanillin"), PROTEINS("proteins"), FATS("fats"),
    /**
     * Elements names.
     */
    CARBOHYDRATES("carbohydrates"), PRODUCTION("production"), INGREDIENTS("ingredients"), VALUE("value"),

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
     * @param value - the constant value.
     */
    XmlEnum(final String value) {
        this.value = value;
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
