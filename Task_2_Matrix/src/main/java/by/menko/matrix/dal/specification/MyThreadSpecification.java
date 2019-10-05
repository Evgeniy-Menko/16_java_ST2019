package by.menko.matrix.dal.specification;

import by.menko.matrix.bean.MyThread;
import by.menko.matrix.dal.Specification;

public class MyThreadSpecification implements Specification {
    @Override
    public int[][] specified(int[][] matrix, int countThreads)
            throws InterruptedException {
        int[][] result = matrix;
        for (int i = 0; i < countThreads; i++) {
            Thread t = new Thread(new MyThread(result));
            t.setName("Thread " + i);
            t.start();
            if (i == countThreads) {
                t.join();
            }
        }
        return result;
    }
}
