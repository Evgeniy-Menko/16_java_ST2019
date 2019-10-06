package by.menko.matrix.dal;

import java.util.List;

public interface MatrixRepository {
    /**
     * Add diagonal values to the storage.
     *
     * @param values .
     */
    void addValuesDiagonal(List<Integer> values);

    /**
     * Get diagonal values.
     *
     * @return diagonal values in the form of a list.
     */
    List<Integer> getValuesDiagonal();

    /**
     * Get count threads.
     *
     * @return count of threads.
     */
    int getThreadsCount();

    /**
     * Add  matrix to the storage.
     *
     * @param matrixValue list values.
     */
    void addMatrix(List<String> matrixValue);

    /**
     * Get Matrix.
     *
     * @return matrix.
     */
    int[][] getMatrix();

    /**
     * Override method for specification.
     *
     * @param specification object specification.
     *
     * @return matrix.
     *
     * @throws InterruptedException .
     */
    int[][] query(Specification specification) throws InterruptedException;
}

