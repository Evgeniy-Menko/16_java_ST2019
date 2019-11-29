package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.DiskDao;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.Film;
import by.menko.finalproject.entity.Game;
import by.menko.finalproject.entity.Music;
import by.menko.finalproject.exception.PersonalException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Optional;

public class DiskDaoImpl extends BaseDao implements DiskDao {
    private static final String CREATE_DISK = "INSERT INTO `disk` (`user_id`, `name`, `genre`,`price`,`type`,`description`,`year`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String CREATE_FILM = "INSERT INTO `disk_info_films` (`disk_id`, `country`, `running_time`) VALUES (?, ?, ?)";
    private static final String CREATE_GAME = "INSERT INTO `disk_info_game` (`disk_id`, `console_types`, `age_limit`,`developer`) VALUES (?, ?, ?, ?)";
    private static final String CREATE_MUSIC = "INSERT INTO `disk_info_music` (`disk_id`, `singer`,`albom`) VALUES (?, ?, ?)";
    private static final String CREATE_IMAGE = "INSERT INTO `image` (`id_image`, `image`,`name_image`) VALUES (?, ?, ?)";

    @Override
    public Integer create(Disk disk) throws PersonalException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE_DISK, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, disk.getIdUser());
            statement.setString(2, disk.getNameDisk());
            statement.setString(3, disk.getGenre());
            statement.setDouble(4, disk.getPrice());

            statement.setString(6, disk.getDescription());
            statement.setDate(7, (Date) disk.getYear());
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
    public void createDisk(Film disk) throws PersonalException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(CREATE_FILM);
            statement.setInt(1, disk.getIdEntity());
            statement.setString(2, disk.getCountry());
            // statement.setDate(3,disk.getRunningTime());
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
    public void createDisk(Game disk) throws PersonalException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(CREATE_GAME);
            statement.setInt(1, disk.getIdEntity());
            statement.setInt(2, disk.getTypeConsole().getIdTypeConsole());
            statement.setInt(3, disk.getAgeLimit());
            statement.setString(4, disk.getDeveloper());
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
    public void createDisk(Music disk) throws PersonalException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(CREATE_MUSIC);
            statement.setInt(1, disk.getIdEntity());
            statement.setString(2, disk.getSinger());
            statement.setString(3, disk.getAlbom());
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
    public void createImage(Disk disk) throws PersonalException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(CREATE_IMAGE);
            statement.setInt(1, disk.getIdEntity());
            statement.setBlob(2, new FileInputStream(disk.getImage()));
            statement.setString(3, disk.getImage().getName());
            statement.executeUpdate();
        } catch (SQLException | FileNotFoundException e) {
            throw new PersonalException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }
}
