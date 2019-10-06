package by.menko.matrix.dal.storage;

import by.menko.matrix.service.parser.ParserString;

import java.util.Arrays;
import java.util.List;

public final class Matrix {
    /**
     * Object class.
     */
    private static Matrix matrix;
    /**
     * count of threads.
     */
    private int countThreads;
    /**
     * Matrix.
     */
    private int[][] matrixArray;
    /**
     * Diagonal values.
     */
    private List<Integer> valuesDiagonal;

    /**
     * Private constructor.
     */
    private Matrix() {

    }

    /**
     * Singleton.
     *
     * @return object matrix.
     */
    public static synchronized Matrix getInstance() {
        if (matrix == null) {
            matrix = new Matrix();
        }
        return matrix;
    }

    /**
     * Get diagonal values.
     *
     * @return list.
     */
    public List<Integer> getValuesDiagonal() {
        return valuesDiagonal;
    }

    /**
     * Setter for diagonal values.
     *
     * @param valueDiagonal .
     */
    public void setValuesDiagonal(final List<Integer> valueDiagonal) {
        this.valuesDiagonal = valueDiagonal;
    }

    /**
     * Getter for countThreads.
     *
     * @return count threads.
     */
    public int getCountThreads() {
        return countThreads;
    }

    /**
     * Adds a matrix from the values list.
     *
     * @param matrixValue .
     */
    public void addMatrix(final List<String> matrixValue) {
        matrixArray = new int[matrixValue.size() - 1]
                [matrixValue.size() - 1];
        countThreads = Integer.parseInt(matrixValue.get(0));
        ParserString p = new ParserString();
        for (int i = 0; i < matrixArray.length; i++) {
            String[] value = p.parserString(matrixValue.get(i + 1));
            for (int j = 0; j < matrixArray[i].length; j++) {
                matrixArray[i][j] = Integer.parseInt(value[j]);
            }
        }
    }

    /**
     * Add diagonal values.
     *
     * @param values .
     */
    public void addValuesDiagonal(final List<Integer> values) {
        valuesDiagonal = values;
    }

    /**
     * Returns a copy of the matrix.
     *
     * @return matrix.
     */
    public int[][] getMatrix() {
        if (matrixArray != null) {
            return Arrays.stream(matrixArray)
                    .map(r -> Arrays.copyOf(r, r.length))
                    .toArray(int[][]::new);
        } else {
            return new int[0][];
        }
    }
}
