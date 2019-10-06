package by.menko.matrix.bean;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LockThreads implements Runnable {
    /**
     * Object ReentrantLock.
     */
    private ReentrantLock locker;
    /**
     * Matrix.
     */
    private int[][] matrix;
    /**
     * Values diagonal.
     */
    private List<Integer> value;
    /**
     * Index and count.
     */
    private AtomicInteger index;
    /**
     * Time sleep.
     */
    private static final long TIME = 100;
    /**
     * Constructor.
     *
     * @param matrixForChange Matrix
     * @param lock            object ReentrantLock
     * @param values          diagonal
     * @param count           counter
     */
    public LockThreads(final int[][] matrixForChange,
                       final ReentrantLock lock,
                       final List<Integer> values,
                       final AtomicInteger count) {
        this.matrix = matrixForChange;
        this.locker = lock;
        this.value = values;
        this.index = count;
    }

    /**
     * Changes the diagonal.
     */
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
                TimeUnit.MILLISECONDS.sleep(TIME);

            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();

            }
        }
    }
}

