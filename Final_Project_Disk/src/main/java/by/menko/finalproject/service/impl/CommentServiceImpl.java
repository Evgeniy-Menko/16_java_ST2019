package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.CommentDao;
import by.menko.finalproject.dao.UserDao;
import by.menko.finalproject.entity.Comment;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.CommentService;


import java.util.*;

public class CommentServiceImpl extends ServiceImpl implements CommentService {
    private static final String REGEX_SENTENCE = "^(?!\\s\\t\\n\\r*$)[A-zА-яЁё0-9,.!@#?:()_\\t\\n\\r ]*$";

    @Override
    public Map<Comment, UserInfo> getComment(final Integer idDisk) throws PersonalException {
        try {
            CommentDao dao = transaction.createDao(TypeServiceAndDao.COMMENT);
            UserDao userDao = transaction.createDao(TypeServiceAndDao.USER);
            List<Comment> listComment = dao.readByIdDisk(idDisk);
            Optional<UserInfo> user;
            Map<Comment, UserInfo> mapCommentAndUser = new LinkedHashMap<>();
            for (Comment comment : listComment) {
                user = userDao.read(comment.getIdUserCommented());
                user.ifPresent(userInfo -> mapCommentAndUser.put(comment, userInfo));
            }
            transaction.commit();
            return mapCommentAndUser;
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }

    @Override
    public void addComment(final String idDisk, final String commentText, final Integer idUser)
            throws PersonalException {
        CommentDao dao = transaction.createDao(TypeServiceAndDao.COMMENT);
        Comment comment = new Comment();
        try {
            Integer id = Integer.parseInt(idDisk);

            if (commentText != null && !commentText.isEmpty() && commentText.matches(REGEX_SENTENCE)) {
                comment.setCommentText(commentText);
                comment.setIdDisk(id);
                comment.setIdUserCommented(idUser);
                dao.create(comment);
                transaction.commit();
            } else {
                throw new PersonalException("Incorrect format comment.");
            }
        } catch (NumberFormatException | PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }

    }

    @Override
    public void deleteComment(final String idComment, final Integer idUser) throws PersonalException {
        CommentDao dao = transaction.createDao(TypeServiceAndDao.COMMENT);
        try {
            Integer idCom = Integer.parseInt(idComment);
            dao.delete(idCom, idUser);
            transaction.commit();
        } catch (NumberFormatException | PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }
}
