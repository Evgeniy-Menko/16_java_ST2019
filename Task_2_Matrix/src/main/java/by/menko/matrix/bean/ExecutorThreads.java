package by.menko.matrix.bean;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class ExecutorThreads implements Runnable {
    /**
     * Matrix.
     */
    private int[][] matrix;
    /**
     * Name thread.
     */
    private String name;
    /**
     * value diagonal.
     */
    private List<Integer> value;
    /**
     * counter object.
     */
    private AtomicInteger count;
    /**
     * Time sleep thread.
     */
    private static final long TIME = 100;

    /**
     * Constructor.
     *
     * @param nameThread      name Thread
     * @param matrixForChange matrix
     * @param values          diagonal go change
     * @param counter         object
     */
    public ExecutorThreads(final String nameThread,
                           final int[][] matrixForChange,
                           final List<Integer> values,
                           final AtomicInteger counter) {
        this.matrix = matrixForChange;
        this.name = nameThread;
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
                if (index < matrix.length && matrix[index][index] == 0) {
                    matrix[index][index] = value.get(index);
                    System.out.println(name + " added " + value.get(index));

                    TimeUnit.MILLISECONDS.sleep(TIME);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

    }
}
