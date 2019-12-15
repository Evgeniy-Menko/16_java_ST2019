package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.ComplaintDao;
import by.menko.finalproject.entity.Complaint;
import by.menko.finalproject.exception.PersonalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ComplaintDaoImpl extends BaseDao implements ComplaintDao {

    private static final String DELETE_COMPLAINT = "DELETE FROM `complaints` WHERE `id_complaint`=?";
    private static final String CREATE_COMPLAINT = "INSERT INTO `complaints` (`user_id_complained`, `disk_id`, `user_was_complained`,`complaint_text`) VALUES (?, ?, ?,?)";
    private static final String GET_COMPLAINT = "SELECT `id_complaint`, `user_id_complained`, `disk_id`,`user_was_complained`,`complaint_text`,`time_added` FROM `complaints` WHERE `id_complaint` = ?";
    private static final String GET_ALL = "SELECT `id_complaint`, `user_id_complained`, `disk_id`,`user_was_complained`,`complaint_text`,`time_added` FROM `complaints` ORDER BY `time_added` DESC ";

    @Override
    public Integer create(Complaint entity) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_COMPLAINT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getUserIdComplained());
            statement.setInt(2, entity.getIdDisk());
            statement.setInt(3, entity.getUserWasComplained());
            statement.setString(4, entity.getTextComplaint());
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
    public Optional<Complaint> read(Integer id) throws PersonalException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.prepareStatement(GET_COMPLAINT);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Complaint complaint = null;
            if (resultSet.next()) {
                complaint = new Complaint();
                complaint.setIdEntity(resultSet.getInt("id_complaint"));
                complaint.setUserIdComplained(resultSet.getInt("user_id_complained"));
                complaint.setIdDisk(resultSet.getInt("disk_id"));
                complaint.setUserWasComplained(resultSet.getInt("user_was_complained"));
                complaint.setTextComplaint(resultSet.getString("complaint_text"));
                complaint.setTimeAdded(resultSet.getTimestamp("time_added"));
            }
            return Optional.of(
                    complaint);
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
    public void delete(Integer id) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_COMPLAINT)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    @Override
    public List<Complaint> readAll() throws PersonalException {
        List<Complaint> result = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                Complaint complaint = null;
                while (resultSet.next()) {
                    complaint = new Complaint();
                    complaint.setIdEntity(resultSet.getInt("id_complaint"));
                    complaint.setUserIdComplained(resultSet.getInt("user_id_complained"));
                    complaint.setIdDisk(resultSet.getInt("disk_id"));
                    complaint.setUserWasComplained(resultSet.getInt("user_was_complained"));
                    complaint.setTextComplaint(resultSet.getString("complaint_text"));
                    complaint.setTimeAdded(resultSet.getTimestamp("time_added"));
                    result.add(complaint);
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
