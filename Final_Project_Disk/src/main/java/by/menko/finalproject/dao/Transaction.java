package by.menko.finalproject.dao;

import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;

public interface Transaction {
    <T extends Dao<?>> T createDao(final TypeServiceAndDao key) throws PersonalException;

    void commit() throws PersonalException;

    void rollback() throws PersonalException;
}
