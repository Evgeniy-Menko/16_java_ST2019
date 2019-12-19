package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.Transaction;
import by.menko.finalproject.service.Service;

public abstract class ServiceImpl implements Service {
    protected Transaction transaction = null;

    public void setTransaction(final Transaction transaction) {
        this.transaction = transaction;
    }
}
