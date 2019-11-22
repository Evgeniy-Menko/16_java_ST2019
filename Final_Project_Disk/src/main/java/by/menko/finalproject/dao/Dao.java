package by.menko.finalproject.dao;

import by.menko.finalproject.entity.Entity;
import by.menko.finalproject.exception.PersonalException;

import java.util.Optional;


public interface Dao<T extends Entity> {

    default Integer create(T entity) throws PersonalException {
        throw new PersonalException();
    }

    default Optional<T> read(Integer identity) throws PersonalException {
        throw new PersonalException();
    }

    default void update(T entity) throws PersonalException {
        throw new PersonalException();
    }

    default void delete(Integer identity) throws PersonalException {
        throw new PersonalException();
    }
}
