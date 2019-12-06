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
    private static final String DELETE_COMMENT = "DELETE FROM `comments` WHERE `id_comment`=? AND `user_id_commented`=?";
    private static final String CREATE_COMMENT = "INSERT INTO `comments` (`user_id_commented`, `disk_id`, `comment_text`) VALUES (?, ?, ?)";
    private static final String GET_COMMENTS = "SELECT `id_comment`, `user_id_commented`,`comment_text`,`time_added` FROM `comments` WHERE `disk_id` = ? ORDER BY `time_added` DESC";

    @Override
    public void delete(Integer id, Integer idUser) throws PersonalException {

        try (PreparedStatement statement = connection.prepareStatement(DELETE_COMMENT)) {
            statement.setInt(1, id);
            statement.setInt(2, idUser);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    @Override
    public Integer create(Comment entity) throws PersonalException {

        try (PreparedStatement statement = connection.prepareStatement(CREATE_COMMENT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getIdUserCommented());
            statement.setInt(2, entity.getIdDisk());
            statement.setString(3, entity.getCommentText());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    throw new PersonalException();
                }
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    @Override
    public List<Comment> readByIdDisk(Integer idDisk) throws PersonalException {
        List<Comment> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(GET_COMMENTS)) {
            statement.setInt(1, idDisk);
            Comment comment = null;
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    comment = new Comment();
                    comment.setIdEntity(resultSet.getInt("id_comment"));
                    comment.setIdUserCommented(resultSet.getInt("user_id_commented"));
                    comment.setCommentText(resultSet.getString("comment_text"));
                    comment.setTimeAdded(resultSet.getTimestamp("time_added"));
                    result.add(comment);
                }
                return result;
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }
}
