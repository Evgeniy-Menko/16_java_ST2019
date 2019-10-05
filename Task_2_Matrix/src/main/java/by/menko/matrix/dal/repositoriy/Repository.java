package by.menko.matrix.dal.repositoriy;

import by.menko.matrix.dal.MatrixRepository;
import by.menko.matrix.dal.Specification;
import by.menko.matrix.dal.matrix.Matrix;

import java.util.List;

public class Repository implements MatrixRepository {
    Matrix storage = Matrix.getInstance();

    @Override
    public void setThreadsCount(int count) {
        storage.setCountThreads(count);
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
    public int[][] query(Specification specification) {
        return specification.specified(storage.getMatrix());
    }
}
