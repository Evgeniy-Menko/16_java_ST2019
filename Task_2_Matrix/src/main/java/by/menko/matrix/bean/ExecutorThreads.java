package by.menko.matrix.bean;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ExecutorThreads implements Runnable {
    int[][] matrix;
    String name;
    List<Integer> value;
    private static AtomicInteger index = new AtomicInteger(0);


    public ExecutorThreads(String name, int[][] matrix, List<Integer> values) {
        this.matrix = matrix;
        this.name = name;
        this.value = values;

    }

    @Override
    public void run() {
        while (index.get() < matrix.length) {
            try {

                if (index.get() < matrix.length && matrix[index.get()][index.get()] == 0) {
                    matrix[index.get()][index.get()] = value.get(index.get());
                    System.out.println(name + " добавил число " + value.get(index.get()));
                    index.incrementAndGet();
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

    }
}
