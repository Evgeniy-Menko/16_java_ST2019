package by.menko.finalproject.service;

import by.menko.finalproject.entity.Comment;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.exception.PersonalException;



import java.util.Map;

public interface CommentService extends Service {
    Map<UserInfo, Comment> getComment(Integer idDisk) throws PersonalException;

    void addComment(String idDisk,String comment,Integer idUser) throws PersonalException;
    void deleteComment(String idComment,Integer idUser) throws PersonalException;
}
