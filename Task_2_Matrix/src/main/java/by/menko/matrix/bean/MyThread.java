package by.menko.matrix.bean;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {
    int index;
    int[][] matrix;
    int[] value = new int[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    private static AtomicInteger count = new AtomicInteger(0);

    public MyThread(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public void run() {

        while (count.get() < matrix.length) {
            try {

                index = count.getAndIncrement();


                if (index < matrix.length && matrix[index][index] == 0) {

                    matrix[index][index] = value[index];

                    System.out.printf("%s %s %d %n", Thread
                                    .currentThread().getName(),
                            "added ", value[index]);

                    TimeUnit.MILLISECONDS.sleep(100);

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

