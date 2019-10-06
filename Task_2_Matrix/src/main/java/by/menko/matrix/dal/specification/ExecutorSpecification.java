package by.menko.matrix.dal.specification;

import by.menko.matrix.bean.ExecutorThreads;
import by.menko.matrix.dal.Specification;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorSpecification implements Specification {
    /**
     * Change diagonal with executor.
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
        ExecutorService executor;
        AtomicInteger count = new AtomicInteger(0);
        int[][] result = matrix;
        executor = Executors.newFixedThreadPool(countThreads);
        for (int i = 0; i < countThreads; i++) {
            executor.execute(new ExecutorThreads("Thread " + i,
                    result, values, count));
        }
        TimeUnit.SECONDS.sleep(1);
        executor.shutdown();
        return result;
    }


}
