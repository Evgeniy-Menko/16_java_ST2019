package by.menko.matrix.service.validate;


import by.menko.matrix.service.parser.ParserString;

import java.util.List;

public class Validator {
    /**
     * Maximum threads.
     */
    private static final int MAXTHREADS = 6;
    /**
     * Minimum threads.
     */
    private static final int MINTHREADS = 4;
    /**
     * Regex.
     */
    private static final String REGEX = "^\\d+$";

    private boolean validateCountThreads(final String count) {
        return count.matches(REGEX)
                && Integer.parseInt(count) <= MAXTHREADS
                && Integer.parseInt(count) >= MINTHREADS;
    }

    private boolean validateStringMatrix(final String[] stringMatrix,
                                         final int sizeMatrix,
                                         final int indexDiagonal) {
        boolean result = false;
        if (stringMatrix.length == sizeMatrix
                && "0".equals(stringMatrix[indexDiagonal])) {
            for (int i = 0; i < stringMatrix.length; i++) {
                if (stringMatrix[i].matches(REGEX)) {
                    result = true;
                } else {
                    return false;
                }
            }
        }
        return result;
    }

    /**
     * Value validation.
     *
     * @param values .
     *
     * @return true or false.
     */
    public boolean validateMatrix(final List<String> values) {
        boolean result = false;
        ParserString parser = new ParserString();
        if (validateCountThreads(values.get(0))) {
            for (int i = 1; i < values.size(); i++) {
                if (validateStringMatrix(parser
                                .parserString(values.get(i)),
                        values.size() - 1,
                        i - 1
                )) {
                    result = true;
                } else {
                    return false;
                }
            }
        }
        return result;
    }

    /**
     * Diagonal value validation.
     *
     * @param values       .
     * @param lengthMatrix .
     *
     * @return true or false.
     */
    public boolean validateValuesDiagonal(final String[] values,
                                          final int lengthMatrix) {
        boolean result = false;
        if (values.length == lengthMatrix) {
            for (int i = 0; i < values.length; i++) {
                if (values[i].matches(REGEX)) {
                    result = true;
                } else {
                    return false;
                }

            }
        }
        return result;
    }

}
