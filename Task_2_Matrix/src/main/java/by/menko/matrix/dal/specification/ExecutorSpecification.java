package by.menko.matrix.dal.specification;

import by.menko.matrix.bean.ExecutorThreads;
import by.menko.matrix.dal.Specification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorSpecification implements Specification {
    ExecutorService executor;

    @Override
    public int[][] specified(int[][] matrix, int countThreads) throws InterruptedException {
        int[][] result = matrix;
        executor = Executors.newFixedThreadPool(countThreads);
        for (int i = 0; i < countThreads; i++) {
            executor.execute(new ExecutorThreads("Thread " + i, result));
        }
        TimeUnit.SECONDS.sleep(1);
        executor.shutdown();
        return result;
    }
}
