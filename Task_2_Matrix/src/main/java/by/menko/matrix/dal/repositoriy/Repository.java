package by.menko.matrix.dal.repositoriy;

import by.menko.matrix.dal.MatrixRepository;
import by.menko.matrix.dal.Specification;
import by.menko.matrix.dal.storage.Matrix;

import java.util.List;

public class Repository implements MatrixRepository {
    Matrix storage = Matrix.getInstance();

    @Override
    public void addValuesDiagonal(List<Integer> values) {
        storage.addValuesDiagonal(values);
    }

    @Override
    public List<Integer> getValuesDiagonal() {
        return storage.getValuesDiagonal();
    }

    @Override
    public int getThreadsCount() {
        return storage.getCountThreads();
    }

    @Override
    public void addMatrix(List<String> matrixValue) {
        storage.addMatrix(matrixValue);
    }

    @Override
    public int[][] getMatrix() {
        return storage.getMatrix();
    }

    @Override
    public int[][] query(Specification specification) throws InterruptedException {
        return specification.specified(getMatrix(), getThreadsCount(), getValuesDiagonal());
    }
}
