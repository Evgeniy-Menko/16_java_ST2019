package by.menko.matrix.dal.specification;

import by.menko.matrix.bean.MyThread;
import by.menko.matrix.dal.Specification;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyThreadSpecification implements Specification {
    @Override
    public int[][] specified(int[][] matrix, int countThreads, List<Integer> values)
            throws InterruptedException {
        int[][] result = matrix;
        for (int i = 0; i < countThreads; i++) {
            Thread t = new Thread(new MyThread(result,values));
            t.setName("Thread " + i);
            t.start();
            if (i == countThreads) {
                t.join();
            }
        }
        TimeUnit.SECONDS.sleep(5);
        return result;
    }
}
