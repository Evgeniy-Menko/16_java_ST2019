package by.menko.matrix.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class SemaphoreThreads extends Thread {
    /**
     * log4j2.
     */
    private Logger logger = LogManager.getLogger();
    /**
     * Object semaphore.
     */
    private Semaphore sem;
    /**
     * Matrix.
     */
    private int[][] matrix;
    /**
     * Value diagonal.
     */
    private List<Integer> value;
    /**
     * Index and counter.
     */
    private AtomicInteger count;
    /**
     * Time sleep.
     */
    private static final long TIME = 10;

    /**
     * Constructor.
     *
     * @param semaphore       object semaphore
     * @param matrixForChange matrix
     * @param values          value diagonal
     * @param counter         counter
     */
    public SemaphoreThreads(final Semaphore semaphore,
                            final int[][] matrixForChange,
                            final List<Integer> values,
                            final AtomicInteger counter) {

        this.sem = semaphore;
        this.matrix = matrixForChange;
        this.value = values;
        this.count = counter;

    }

    /**
     * Changes the diagonal.
     */
    @Override
    public void run() {
        while (count.get() < matrix.length) {
            try {
                int index = count.getAndIncrement();
                logger.info(Thread.currentThread().getName()
                        + " awaiting");
                sem.acquire();
                logger.info(Thread.currentThread().getName()
                        + " performing");

                if (index < matrix.length
                        && matrix[index][index] == 0) {
                    matrix[index][index] = value.get(index);
                    logger.info(Thread.currentThread().getName()
                            + " added " + value.get(index));
                }

                TimeUnit.MILLISECONDS.sleep(TIME);
                logger.info(Thread.currentThread().getName()
                        + " finished");
                sem.release();


            } catch (InterruptedException e) {
                logger.error(Thread.currentThread().getName()
                        + "dead");
                Thread.currentThread().interrupt();

            }
        }
    }

}
