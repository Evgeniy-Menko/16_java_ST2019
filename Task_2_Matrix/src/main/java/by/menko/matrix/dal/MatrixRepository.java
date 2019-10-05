package by.menko.matrix.dal;

import java.util.List;

public interface MatrixRepository {
    void setThreadsCount(int count);

    int getThreadsCount();

    void addMatrix(List<String> matrixValue);

    int[][] getMatrix();

    int[][] query(Specification specification);
}