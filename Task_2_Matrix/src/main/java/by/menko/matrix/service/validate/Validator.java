package by.menko.matrix.service.validate;

import by.menko.matrix.dal.repositoriy.Repository;
import by.menko.matrix.service.parser.ParserString;

import java.util.List;

public class Validator {
    private static final int MAXTHREADS = 6;
    private static final int MINTHREADS = 4;

    private boolean validateCountThreads(final String count) {
        String regex = "^\\d+$";
        return count.matches(regex)
                && Integer.parseInt(count) <= MAXTHREADS
                && Integer.parseInt(count) >= MINTHREADS;
    }

    private boolean validateStringMatrix(final String[] stringMatrix,
                                         final int sizeMatrix,
                                         final int indexDiagonal) {
        boolean result = false;
        String regex = "^\\d+$";
        if (stringMatrix.length == sizeMatrix
                && "0".equals(stringMatrix[indexDiagonal])) {
            for (int i = 0; i < stringMatrix.length; i++) {
                if (stringMatrix[i].matches(regex)) {
                    result = true;
                } else {
                    return false;
                }
            }
        }
        return result;
    }

    public boolean validateMatrix(final List<String> matrix) {
        boolean result = false;
        ParserString parser = new ParserString();
        if (validateCountThreads(matrix.get(0))) {
            for (int i = 1; i < matrix.size(); i++) {
                if (validateStringMatrix(parser
                                .parserString(matrix.get(i)),
                        matrix.size() - 1,
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

    public boolean validateValuesDiagonal(String[] values) {
        boolean result = false;
        String regex = "^\\d+$";
        int lengthMatrix = new Repository().getMatrix().length;
        if (values.length == lengthMatrix) {
            for (int i = 0; i < values.length; i++) {
                if (values[i].matches(regex)) {
                    result = true;
                } else {
                    return false;
                }

            }
        }
        return result;
    }

}
