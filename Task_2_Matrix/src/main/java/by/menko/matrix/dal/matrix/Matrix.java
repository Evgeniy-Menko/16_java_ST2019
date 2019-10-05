package by.menko.matrix.dal.matrix;

import by.menko.matrix.service.parser.ParserString;

import java.util.List;

public class Matrix {

    private static Matrix matrix;

    private int countThreads;

    private int[][] matrixArray;

    private Matrix() {

    }

    public static synchronized Matrix getInstance() {
        if (matrix == null) {
            matrix = new Matrix();
        }
        return matrix;
    }

    public int getCountThreads() {
        return countThreads;
    }

    public void setCountThreads(int countThreads) {
        this.countThreads = countThreads;
    }

    public void addMatrix(List<String> matrixValue) {
        matrixArray = new int[matrixValue.size() - 1]
                [matrixValue.size() - 1];
        ParserString p = new ParserString();
        for (int i = 0; i < matrixArray.length; i++) {
            String[] value = p.parserString(matrixValue.get(i + 1));
            for (int j = 0; j < matrixArray[i].length; j++) {
                matrixArray[i][j] = Integer.parseInt(value[j]);
            }
        }
    }

    public int[][] getMatrix() {
        return matrixArray.clone();
    }
}
