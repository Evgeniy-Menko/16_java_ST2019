package by.menko.finalproject.dao;

public interface TransactionFactory {
    Transaction createTransaction();

    void close();
}
