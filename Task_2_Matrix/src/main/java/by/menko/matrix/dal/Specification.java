package by.menko.matrix.dal;

import java.util.List;


public interface Specification {
    /**
     * @param matrix       .
     * @param countThreads .
     * @param values       diagonal values.
     *
     * @return matrix.
     *
     * @throws InterruptedException .
     */
    int[][] specified(int[][] matrix, int countThreads,
                      List<Integer> values) throws InterruptedException;
}
