package by.menko.finalproject.service;

import by.menko.finalproject.entity.Comment;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.dao.exception.PersonalException;


import java.util.Map;

public interface CommentService extends Service {
    /**
     * get comment for disk by id disk.
     *
     * @param idDisk integer id disk.
     *
     * @return map with info user and his comment.
     *
     * @throws PersonalException sql exception.
     */
    Map<Comment, UserInfo> getComment(final Integer idDisk) throws PersonalException;

    /**
     * Add comment to announcement.
     *
     * @param idDisk  id disk.
     * @param comment comment text.
     * @param idUser  id user.
     *
     * @throws PersonalException sql exception.
     */
    void addComment(final String idDisk, final String comment, final Integer idUser) throws PersonalException;

    /**
     * delete comment by id user .
     *
     * @param idComment id comment.
     * @param idUser    id user.
     *
     * @throws PersonalException sql exception.
     */
    void deleteComment(final String idComment, final Integer idUser) throws PersonalException;
}
