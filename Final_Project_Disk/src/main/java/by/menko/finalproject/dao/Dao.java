package by.menko.finalproject.dao;

import by.menko.finalproject.entity.Entity;
import by.menko.finalproject.dao.exception.PersonalException;

import java.util.Optional;


public interface Dao<T extends Entity> {

    default Integer create(final T entity) throws PersonalException {
        throw new PersonalException();
    }

    default Optional<T> read(final Integer identity) throws PersonalException {
        throw new PersonalException();
    }

    default void update(final T entity) throws PersonalException {
        throw new PersonalException();
    }

    default void delete(final Integer identity, final Integer idUser) throws PersonalException {
        throw new PersonalException();
    }
}
