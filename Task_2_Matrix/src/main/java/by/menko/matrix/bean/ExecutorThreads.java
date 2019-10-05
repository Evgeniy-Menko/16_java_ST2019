package by.menko.matrix.bean;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorThreads implements Runnable {
    int[][] matrix;
    String name;
    int[] value = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static int index = 0;


    public ExecutorThreads(String name, int[][] matrix) {
        this.matrix = matrix;
        this.name = name;


    }

    @Override
    public void run() {
        while (index < matrix.length ) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                if ( index < matrix.length && matrix[index][index] == 0) {
                    matrix[index][index] = value[index];
                    System.out.println(name + " добавил число " + value[index]);
                    index++;
                    TimeUnit.MILLISECONDS.sleep(100);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }

    }
}
