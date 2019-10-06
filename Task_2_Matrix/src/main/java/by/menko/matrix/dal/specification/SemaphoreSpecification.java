package by.menko.matrix.dal.specification;

import by.menko.matrix.bean.SemaphoreThreads;
import by.menko.matrix.dal.Specification;

import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreSpecification implements Specification {
    @Override
    public int[][] specified(final int[][] matrix, final int countThreads, List<Integer> values)
            throws InterruptedException {
        int[][] result = matrix;
        Semaphore sem = new Semaphore(countThreads / 2, true);
        for (int i = 0; i <= countThreads; i++) {
            Thread t = new Thread(
                    new SemaphoreThreads(sem, "Threads " + i, result, values));
            t.start();
            t.join();

        }
        return result;
    }
}
