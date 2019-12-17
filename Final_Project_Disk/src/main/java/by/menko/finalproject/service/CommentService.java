package by.menko.finalproject.service;

import by.menko.finalproject.entity.Comment;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.dao.exception.PersonalException;


import java.util.Map;

public interface CommentService extends Service {
    Map<UserInfo, Comment> getComment(final Integer idDisk) throws PersonalException;

    void addComment(final String idDisk, final String comment, final Integer idUser) throws PersonalException;

    void deleteComment(final String idComment, final Integer idUser) throws PersonalException;
}
