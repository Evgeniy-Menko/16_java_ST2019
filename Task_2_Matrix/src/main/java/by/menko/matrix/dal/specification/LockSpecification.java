package by.menko.matrix.dal.specification;

import by.menko.matrix.bean.LockThreads;
import by.menko.matrix.dal.Specification;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LockSpecification implements Specification {
    ReentrantLock locker = new ReentrantLock();

    @Override
    public int[][] specified(int[][] matrix, int countThreads, List<Integer> values) throws InterruptedException {
        AtomicInteger index = new AtomicInteger(0);
        int[][] result = matrix;
        for (int i = 0; i < countThreads; i++) {
            Thread t = new Thread(new LockThreads(result, locker, values,index));
            t.setName("Thread " + i);
            t.start();
            if (i == countThreads - 1) {
                t.join();
            }

        }
        return result;
    }
}
