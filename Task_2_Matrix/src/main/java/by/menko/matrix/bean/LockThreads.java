package by.menko.matrix.bean;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockThreads implements Runnable {
    ReentrantLock locker;
    int[][] matrix;

    int[] value = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static int index = 0;


    public LockThreads(int[][] matrix, ReentrantLock lock) {
        this.matrix = matrix;
        this.locker = lock;

    }

    @Override
    public void run() {
        while (index < matrix.length) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                locker.lock();

                if (matrix[index][index] == 0) {
                    matrix[index][index] = value[index];
                    System.out.printf("%s %s %d %n", Thread
                                    .currentThread().getName(),
                            "added ", value[index]);
                    index++;
                    locker.unlock();

                    TimeUnit.MILLISECONDS.sleep(100);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                locker.unlock();
            }
        }

    }

}

