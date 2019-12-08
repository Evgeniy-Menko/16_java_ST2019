package by.menko.finalproject.dao;

import by.menko.finalproject.entity.Complaint;
import by.menko.finalproject.exception.PersonalException;

import java.util.List;

public interface ComplaintDao extends Dao<Complaint> {
    List<Complaint> readAll() throws PersonalException;

    void delete(Integer id) throws PersonalException;
}
