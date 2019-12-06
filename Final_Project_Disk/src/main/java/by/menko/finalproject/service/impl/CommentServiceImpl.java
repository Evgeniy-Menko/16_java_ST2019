package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.CommentDao;
import by.menko.finalproject.dao.UserDao;
import by.menko.finalproject.entity.Comment;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommentServiceImpl extends ServiceImpl implements CommentService {
    private final static String REGEX_SENTENCE = "^(?!\\s\\t\\n\\r*$)[A-zА-яЁё0-9\\S_\\t\\n\\r ]*$";

    @Override
    public Map<UserInfo, Comment> getComment(Integer idDisk) throws PersonalException {
        CommentDao dao = transaction.createDao(TypeServiceAndDao.COMMENT);
        UserDao userDao = transaction.createDao(TypeServiceAndDao.USER);
        List<Comment> listComment = dao.readByIdDisk(idDisk);
        Optional<UserInfo> user;
        Map<UserInfo, Comment> mapCommentAndUser = new HashMap<>();
        for (Comment comment : listComment) {
            user = userDao.read(comment.getIdUserCommented());
            user.ifPresent(userInfo -> mapCommentAndUser.put(userInfo, comment));
        }

        return mapCommentAndUser;
    }

    @Override
    public void addComment(String idDisk, String commentText, Integer idUser) throws PersonalException {
        CommentDao dao = transaction.createDao(TypeServiceAndDao.COMMENT);
        Comment comment = new Comment();
        try {
            Integer id = Integer.parseInt(idDisk);

            if (commentText != null && !commentText.isEmpty() && commentText.matches(REGEX_SENTENCE)) {
                comment.setCommentText(commentText);
                comment.setIdDisk(id);
                comment.setIdUserCommented(idUser);
                dao.create(comment);
            } else {
                throw new PersonalException();
            }
        } catch (NumberFormatException e) {
            throw new PersonalException();
        }

    }

    @Override
    public void deleteComment(String idComment, Integer idUser) throws PersonalException {
        CommentDao dao = transaction.createDao(TypeServiceAndDao.COMMENT);
        try {
            Integer idCom = Integer.parseInt(idComment);
            dao.delete(idCom, idUser);
        } catch (NumberFormatException e) {
            throw new PersonalException();
        }
    }
}
