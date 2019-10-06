package by.menko.matrix.bean;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {
    int index;
    int[][] matrix;
    List<Integer> value;
    private static AtomicInteger count = new AtomicInteger(0);

    public MyThread(int[][] matrix, List<Integer> values) {
        this.matrix = matrix;
        this.value = values;
    }

    @Override
    public void run() {

        while (count.get() < matrix.length) {
            try {

                index = count.getAndIncrement();


                if (index < matrix.length && matrix[index][index] == 0) {

                    matrix[index][index] = value.get(index);

                    System.out.printf("%s %s %d %n", Thread
                                    .currentThread().getName(),
                            "added ", value.get(index));

                    TimeUnit.MILLISECONDS.sleep(100);

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

