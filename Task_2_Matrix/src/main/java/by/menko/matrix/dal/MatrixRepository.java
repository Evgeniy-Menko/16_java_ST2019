package by.menko.matrix.dal;

import java.util.List;

public interface MatrixRepository {
    void addValuesDiagonal(List<Integer> values);

    List<Integer> getValuesDiagonal();

    int getThreadsCount();

    void addMatrix(List<String> matrixValue);

    int[][] getMatrix();

    int[][] query(Specification specification) throws InterruptedException;
}