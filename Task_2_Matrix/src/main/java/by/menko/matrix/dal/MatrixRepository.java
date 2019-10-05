package by.menko.matrix.dal;

import java.util.List;

public interface MatrixRepository {

    int getThreadsCount();

    void addMatrix(List<String> matrixValue);

    int[][] getMatrix();

    int[][] query(Specification specification);
}