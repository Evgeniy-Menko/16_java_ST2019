package by.menko.matrix.dal.storage;

import by.menko.matrix.service.parser.ParserString;

import java.util.Arrays;
import java.util.List;

public class Matrix {

    private static Matrix matrix;

    private int countThreads;

    private int[][] matrixArray;
    private List<Integer> valuesDiagonal;

    private Matrix() {

    }

    public static synchronized Matrix getInstance() {
        if (matrix == null) {
            matrix = new Matrix();
        }
        return matrix;
    }

    public List<Integer> getValuesDiagonal() {
        return valuesDiagonal;
    }

    public void setValuesDiagonal(List<Integer> valuesDiagonal) {
        this.valuesDiagonal = valuesDiagonal;
    }

    public int getCountThreads() {
        return countThreads;
    }

    public void addMatrix(List<String> matrixValue) {
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

    public void addValuesDiagonal(List<Integer> values) {
        valuesDiagonal = values;
    }

    public int[][] getMatrix() {
        return Arrays.stream(matrixArray)
                .map(r -> Arrays.copyOf(r, r.length))
                .toArray(int[][]::new);
    }
}
