package by.menko.matrix.bean;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LockThreads implements Runnable {
    ReentrantLock locker;

    int[][] matrix;
    List<Integer> value;

     AtomicInteger index ;


    public LockThreads(int[][] matrix, ReentrantLock lock, List<Integer> values,AtomicInteger index) {
        this.matrix = matrix;
        this.locker = lock;
        this.value = values;
        this.index = index;
    }

    @Override
    public void run() {

        while (index.get() < matrix.length) {
            try {

                locker.lock();
                if (matrix[index.get()][index.get()] == 0) {
                    matrix[index.get()][index.get()] = value.get(index.get());
                    System.out.printf("%s %s %d %n", Thread
                                    .currentThread().getName(),
                            "added ", value.get(index.get()));
                    index.incrementAndGet();
                }


                locker.unlock();
                TimeUnit.MILLISECONDS.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();

            }
        }

      /*  if (index.get() >= matrix.length) {
            index.set(0);
            return;
        }*/

    }

}

