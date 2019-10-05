package by.menko.matrix.bean;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreThreads extends Thread {

    Semaphore sem;
    int[][] matrix;
    int num = 0;

    String name;
    int[] value = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static AtomicInteger index = new AtomicInteger(0);


    public SemaphoreThreads(Semaphore sem, String name, int[][] matrix) {

        this.sem = sem;
        this.name = name;
        this.matrix = matrix;

    }

    public void run() {
        while (index.get() < matrix.length) {
            try {
                System.out.println(name + " ожидает");
                sem.acquire();

                System.out.println(name + " выполняет");
                if (index.get() < matrix.length && matrix[index.get()][index.get()] == 0) {
                    matrix[index.get()][index.get()] = value[index.get()];
                    System.out.println(name + " добавил число " + value[index.get()]);
                    index.incrementAndGet();
                }

                TimeUnit.MILLISECONDS.sleep(10);
                System.out.println(name + " ушёл");
                sem.release();


            } catch (InterruptedException e) {

                System.out.println(name + "dead");
            }
        }
    }

}
