package by.menko.matrix.bean;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreThreads extends Thread {

    Semaphore sem;
    int[][] matrix;
    int num = 0;

    String name;
    int[] value = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
     int index = 0;


    public SemaphoreThreads(Semaphore sem, String name, int[][] matrix ,int index) {

        this.sem = sem;
        this.name = name;
        this.matrix = matrix;
         this.index = index;
    }

    public void run() {

        try {
            System.out.println(name + " ожидает");
            sem.acquire();

            System.out.println(name + " выполняет");
            if (matrix[index][index] == 0) {
                matrix[index][index] = value[index];
                System.out.println(name + " добавил число " + value[index]);

            }

            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(name + " ушёл");
            sem.release();


        } catch (InterruptedException e) {

            System.out.println(name + "dead");
        }
    }

}
