package by.menko.matrix.bean;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {
    ReentrantLock locker;
    int[][] matrix;
    int[] value = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static AtomicInteger index = new AtomicInteger(0);

    public MyThread(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public void run() {
        while (index.get() < matrix.length) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);


                if (index.get() < matrix.length && matrix[index.get()][index.get()] == 0) {
                    matrix[index.get()][index.get()] = value[index.get()];
                    System.out.printf("%s %s %d %n", Thread
                                    .currentThread().getName(),
                            "added ", value[index.get()]);
                    index.incrementAndGet();
                }


                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
