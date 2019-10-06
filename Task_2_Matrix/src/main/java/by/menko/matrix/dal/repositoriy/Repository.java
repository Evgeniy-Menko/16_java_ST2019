package by.menko.matrix.dal.repositoriy;

import by.menko.matrix.dal.MatrixRepository;
import by.menko.matrix.dal.Specification;
import by.menko.matrix.dal.storage.Matrix;

import java.util.List;

public class Repository implements MatrixRepository {
    /**
     * Object class Storage.
     */
    private Matrix storage = Matrix.getInstance();

    /**
     * Add diagonal values to the storage.
     *
     * @param values diagonal.
     */
    @Override
    public void addValuesDiagonal(final List<Integer> values) {
        storage.addValuesDiagonal(values);
    }

    /**
     * Get diagonal values.
     *
     * @return diagonal values in the form of a list.
     */
    @Override
    public List<Integer> getValuesDiagonal() {
        return storage.getValuesDiagonal();
    }

    /**
     * Get count threads.
     *
     * @return count of threads.
     */
    @Override
    public int getThreadsCount() {
        return storage.getCountThreads();
    }

    /**
     * Add  matrix to the storage.
     *
     * @param matrixValue list values.
     */
    @Override
    public void addMatrix(final List<String> matrixValue) {
        storage.addMatrix(matrixValue);
    }

    /**
     * Get Matrix.
     *
     * @return matrix.
     */
    @Override
    public int[][] getMatrix() {
        return storage.getMatrix();
    }

    /**
     * Override method for specification.
     *
     * @param specification object specification.
     *
     * @return matrix.
     */
    @Override
    public int[][] query(final Specification specification)
            throws InterruptedException {
        return specification.specified(getMatrix(),
                getThreadsCount(), getValuesDiagonal());
    }
}
