package by.menko.finalproject.dao.impl;

import java.sql.Connection;

abstract class BaseDao {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
