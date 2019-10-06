package by.menko.matrix.bean;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreThreads extends Thread {

    Semaphore sem;
    int[][] matrix;
    int num = 0;

    String name;
    List<Integer> value;
    private static AtomicInteger index = new AtomicInteger(0);


    public SemaphoreThreads(Semaphore sem, String name, int[][] matrix, List<Integer> values) {

        this.sem = sem;
        this.name = name;
        this.matrix = matrix;
        this.value = values;

    }

    public void run() {
        while (index.get() < matrix.length) {
            try {
                System.out.println(name + " ожидает");
                sem.acquire();

                System.out.println(name + " выполняет");
                if (index.get() < matrix.length && matrix[index.get()][index.get()] == 0) {
                    matrix[index.get()][index.get()] = value.get(index.get());
                    System.out.println(name + " добавил число " + value.get(index.get()));
                    index.incrementAndGet();
                }

                TimeUnit.MILLISECONDS.sleep(10);
                System.out.println(name + " ушёл");
                sem.release();


            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                System.out.println(name + "dead");
            }
        }
    }

}
