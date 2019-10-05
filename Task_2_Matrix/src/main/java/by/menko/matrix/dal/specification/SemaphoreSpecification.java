package by.menko.matrix.dal.specification;

import by.menko.matrix.bean.SemaphoreThreads;
import by.menko.matrix.dal.Specification;

import java.util.concurrent.Semaphore;

public class SemaphoreSpecification implements Specification {
    @Override
    public int[][] specified(final int[][] matrix, final int countThreads)
            throws InterruptedException {
        int[][] result = matrix;
        Semaphore sem = new Semaphore(countThreads / 2, true);
        for (int i = 0; i <= countThreads; i++) {
            Thread t = new Thread(
                    new SemaphoreThreads(sem, "Threads " + i, result));
            t.start();
            if (i == countThreads) {
                t.join();
            }
        }
        return result;
    }
}
