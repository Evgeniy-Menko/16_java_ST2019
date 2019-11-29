package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.CommentDao;
import by.menko.finalproject.entity.Comment;

import by.menko.finalproject.exception.PersonalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CommentDaoImpl extends BaseDao implements CommentDao {
    private static final String DELETE_COMMENT = "DELETE FROM `comments` WHERE `id_comment`=?";
    private static final String CREATE_COMMENT = "INSERT INTO `comment` (`user_id_commented`, `disk_id`, `comment_text`) VALUES (?, ?, ?)";
    private static final String GET_COMMENTS = "SELECT `id_comment`, `user_id_commented`, `disk_id`,`comment_text`,`time_added` FROM `complaints` WHERE `disk_id` = ?";
    @Override
    public void delete(Integer id) throws PersonalException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_COMMENT);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public Integer create(Comment entity) throws PersonalException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE_COMMENT, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getIdUserCommented());
            statement.setInt(2, entity.getIdDisk());
            statement.setString(3, entity.getCommentText());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                //		logger.error("There is no autoincremented index after trying to add record into table `users`");
                throw new PersonalException();
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public List<Comment> readByIdDisk(Integer idDisk) throws PersonalException {
        List<Comment> result = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(GET_COMMENTS);
            resultSet = statement.executeQuery();
            Comment comment = null;
            if (resultSet.next()) {
                comment = new Comment();
                comment.setIdEntity(resultSet.getInt("id_comment"));
                comment.setIdUserCommented(resultSet.getInt("user_id_comment"));
                comment.setIdDisk(resultSet.getInt("disk_id"));
                comment.setCommentText(resultSet.getString("comment_text"));
                comment.setTimeAdded(resultSet.getDate("time_added"));
                result.add(comment);
            }
            return result;
        } catch (SQLException e) {
            throw new PersonalException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }
}
