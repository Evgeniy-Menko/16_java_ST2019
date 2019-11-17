package by.menko.finalproject.dao;

import by.menko.finalproject.entity.Entity;

public interface Dao<T extends Entity> {

    Integer create(T entity);

    T read(Integer identity);

    void update(T entity);

    void delete(Integer identity);
}
