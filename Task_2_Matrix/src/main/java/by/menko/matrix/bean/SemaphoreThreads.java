package by.menko.matrix.bean;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreThreads extends Thread {
    /**
     * Object semaphore.
     */
    private Semaphore sem;
    /**
     * Matrix.
     */
    private int[][] matrix;
    /**
     * Name Thread.
     */
    private String name;
    /**
     * Value diagonal.
     */
    private List<Integer> value;
    /**
     * Index and counter.
     */
    private AtomicInteger index;
    /**
     * Time sleep.
     */
    private static final long TIME = 10;

    /**
     * Constructor.
     *
     * @param semaphore       object semaphore
     * @param nameThread      name thread
     * @param matrixForChange matrix
     * @param values          value diagonal
     * @param count           counter
     */
    public SemaphoreThreads(final Semaphore semaphore,
                            final String nameThread,
                            final int[][] matrixForChange,
                            final List<Integer> values,
                            final AtomicInteger count) {

        this.sem = semaphore;
        this.name = nameThread;
        this.matrix = matrixForChange;
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
                System.out.println(name + " awaiting");
                sem.acquire();

                System.out.println(name + " performs");
                if (index.get() < matrix.length
                        && matrix[index.get()][index.get()] == 0) {
                    matrix[index.get()][index.get()] = value.get(index.get());
                    System.out.println(name
                            + " added " + value.get(index.get()));
                    index.incrementAndGet();
                }

                TimeUnit.MILLISECONDS.sleep(TIME);
                System.out.println(name + " \n"
                        + "released");
                sem.release();


            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                System.out.println(name + "dead");
            }
        }
    }

}
