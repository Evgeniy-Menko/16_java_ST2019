package by.menko.matrix.bean;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class MyThread extends Thread {
    /**
     * Matrix.
     */
    private int[][] matrix;
    /**
     * Value diagonal.
     */
    private List<Integer> value;
    /**
     * counter.
     */
    private AtomicInteger count;
    /**
     * Time sleep.
     */
    private static final long TIME = 100;
    /**
     * Constructor.
     *
     * @param matrixForChange matrix
     * @param values          diagonal
     * @param counter         counter
     */
    public MyThread(final int[][] matrixForChange,
                    final List<Integer> values,
                    final AtomicInteger counter) {
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


                if (index < matrix.length && matrix[index][index] == 0) {

                    matrix[index][index] = value.get(index);

                    System.out.printf("%s %s %d %n", Thread
                                    .currentThread().getName(),
                            "added ", value.get(index));

                    TimeUnit.MILLISECONDS.sleep(TIME);

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

