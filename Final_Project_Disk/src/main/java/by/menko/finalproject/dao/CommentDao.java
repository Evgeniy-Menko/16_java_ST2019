package by.menko.finalproject.dao;

import by.menko.finalproject.entity.Comment;
import by.menko.finalproject.dao.exception.PersonalException;

import java.util.List;

public interface CommentDao extends Dao<Comment> {
    List<Comment> readByIdDisk(final Integer idDisk) throws PersonalException;
}
