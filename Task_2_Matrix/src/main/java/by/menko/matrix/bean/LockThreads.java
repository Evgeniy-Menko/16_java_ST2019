package by.menko.matrix.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LockThreads implements Runnable {
    /**
     * log4j2.
     */
    private Logger logger = LogManager.getLogger();
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
                    logger.info(Thread.currentThread().getName()
                            + " added " + value.get(index.get()));
                    index.incrementAndGet();
                }


                locker.unlock();
                TimeUnit.MILLISECONDS.sleep(TIME);

            } catch (InterruptedException e) {
                logger.error(Thread.currentThread().getName()
                        + "dead");
                Thread.currentThread().interrupt();

            }
        }
    }
}

