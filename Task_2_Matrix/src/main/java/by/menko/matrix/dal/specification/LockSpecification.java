package by.menko.matrix.dal.specification;

import by.menko.matrix.bean.LockThreads;
import by.menko.matrix.dal.Specification;

import java.util.concurrent.locks.ReentrantLock;

public class LockSpecification implements Specification {
    ReentrantLock locker = new ReentrantLock();

    @Override
    public int[][] specified(int[][] matrix, int countThreads) throws InterruptedException {
        int[][] result = matrix;
        for (int i = 0; i < countThreads; i++) {
            Thread t = new Thread(new LockThreads(result, locker));
            t.setName("Thread " + i);
            t.start();
            if (i == countThreads) {
                t.join();
            }
        }
        return result;
    }
}
