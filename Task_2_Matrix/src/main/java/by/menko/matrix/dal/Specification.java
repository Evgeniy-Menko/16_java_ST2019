package by.menko.matrix.dal;

import java.util.List;
import java.util.Map;

public interface Specification {
    int[][] specified(int[][] matrix, int countThreads, List<Integer> values) throws InterruptedException;
}
