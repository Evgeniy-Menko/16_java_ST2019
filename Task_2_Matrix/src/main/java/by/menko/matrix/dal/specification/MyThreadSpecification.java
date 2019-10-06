package by.menko.matrix.dal.specification;

import by.menko.matrix.bean.MyThread;
import by.menko.matrix.dal.Specification;

import java.util.List;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadSpecification implements Specification {
    /**
     * Change diagonal with Mythread.
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
        for (int i = 0; i < countThreads; i++) {
            Thread t = new MyThread(result, values, count);
            t.setName("Thread " + i);
            t.start();
            if (i == countThreads - 1) {
                t.join();
            }
        }
        return result;
    }
}
