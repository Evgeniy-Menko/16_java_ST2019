package by.menko.matrix.dal.specification;

import by.menko.matrix.bean.SemaphoreThreads;
import by.menko.matrix.dal.Specification;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreSpecification implements Specification {
    /**
     * Change diagonal with semaphore.
     *
     * @param matrix       matrix.
     * @param countThreads count of threads.
     * @param values       diagonal values.
     *
     * @return matrix.
     *
     * @throws InterruptedException
     */

    @Override
    public int[][] specified(final int[][] matrix,
                             final int countThreads,
                             final List<Integer> values)
            throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        int[][] result = matrix;
        Semaphore sem = new Semaphore(countThreads / 2, true);
        for (int i = 0; i <= countThreads; i++) {
            Thread t =
                    new SemaphoreThreads(sem, "Threads " + i,
                            result, values, count);
            t.start();
            if (i == countThreads - 1) {
                t.join();
            }
        }
        TimeUnit.SECONDS.sleep(1);
        return result;
    }
}
