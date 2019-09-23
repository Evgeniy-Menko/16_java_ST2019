package by.menko.present.service.validate;

/**
 * @author Evgeniy Menko
 */
public class Validator {
    /**
     * Maximum sweet's weight.
     */
    private final int maxWeight = 500;
    /**
     * maximum percent for sugar.
     */
    private final double maxpercent = 0.2;
    /**
     * Index sweet's type.
     */
    private final int indexType = 0;
    /**
     * Index sweet's name.
     */
    private final int indexName = 1;
    /**
     * Index sweet's weight.
     */
    private final int indexWeight = 2;
    /**
     * Index sweet's sugar.
     */
    private final int indexSugar = 3;
    /**
     * Index sweet's cost.
     */
    private final int indexCost = 4;
    /**
     * Index sweet's another parameter.
     */
    private final int indexAnotherParam = 5;
    /**
     * Length string array with parameters.
     */
    private final int lengtParametrs = 6;

    private boolean validateString(final String name) {
        String regex = "^[a-zA-Z]+$";
        return name.matches(regex);
    }

    private boolean validateWeight(final String weight) {
        String regex = "^\\d+$";
        return weight.matches(regex)
                && Integer.parseInt(weight) <= maxWeight;
    }

    private boolean validateSugar(final String sugar, final String weight) {
        String regex = "^\\d+$";
        int percentSugar = (int)
                (Integer.parseInt(weight) * maxpercent);
        return sugar.matches(regex)
                && Integer.parseInt(sugar) <= percentSugar;
    }

    private boolean validateCost(final String cost) {
        String regex = "^\\d+$";
        return cost.matches(regex) && !("0").equals(cost);
    }

    private boolean validateType(final String type) {
        return ("chocolate").equals(type)
                || ("cookie").equals(type)
                || ("marshmallows").equals(type);
    }

    /**
     * Validator String array for object sweet.
     *
     * @param param String array with parameter.
     *
     * @return true or false.
     */
    public boolean isValidatorSweets(final String[] param) {
        if (param.length == lengtParametrs) {
            return validateType(param[0])
                    && validateString(param[indexName])
                    && validateWeight(param[indexWeight])
                    && validateSugar(param[indexSugar], param[indexWeight])
                    && validateCost(param[indexCost])
                    && validateString(param[indexAnotherParam]);
        } else {
            return false;
        }
    }

    /**
     * Validator String array for object present.
     *
     * @param param String array with parameter.
     *
     * @return true or false.
     */
    public boolean isValidatorPresents(final String[] param) {
        if (param.length == 1) {
            return validateString(param[0]);
        } else {
            return false;
        }
    }

    /**
     * Validator for string.
     *
     * @param param String with parameter.
     *
     * @return true or false.
     */
    public boolean isValidatorPresents(final String param) {
        return validateString(param);
    }

    /**
     * Validator for number.
     *
     * @param param String with parameter.
     *
     * @return true or false.
     */
    public boolean isValidatorNumber(final String param) {
        String regex = "^\\d+$";
        return param.matches(regex);
    }

}

