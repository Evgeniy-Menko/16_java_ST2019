package by.menko.finalproject.dao.impl;

import by.menko.finalproject.dao.DiskDao;
import by.menko.finalproject.entity.*;
import by.menko.finalproject.entity.enumtype.Role;
import by.menko.finalproject.exception.PersonalException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiskDaoImpl extends BaseDao implements DiskDao {
    private static final String CREATE_DISK = "INSERT INTO `disk` (`user_id`, `name`, `genre`,`price`,`type`,`description`,`year`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String CREATE_FILM = "INSERT INTO `disk_info_films` (`disk_id`, `country`, `running_time`) VALUES (?, ?, ?)";
    private static final String CREATE_GAME = "INSERT INTO `disk_info_game` (`disk_id`, `console_types`, `age_limit`,`developer`) VALUES (?, ?, ?, ?)";
    private static final String CREATE_MUSIC = "INSERT INTO `disk_info_music` (`disk_id`, `singer`,`albom`) VALUES (?, ?, ?)";
    private static final String CREATE_IMAGE = "INSERT INTO `image` (`id_image`, `image`,`name_image`) VALUES (?, ?, ?)";
    private static final String GET_DISK_BY_PARAMETER = "SELECT `id_disk`,`name`,`image`,`price`,`time_added` FROM `disk` INNER JOIN `genre` ON `genre_id` = `id_genre`  WHERE  ";
    private static final String GET_ALL = "SELECT `id_disk`,`name`,`image`,`price`,`time_added` FROM `disk` ";

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
            statement.setInt(7, disk.getYear());
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
            statement.setString(3, disk.getImage());
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

    @Override
    public List<Disk> readDiskByParameter(Integer type, Integer genre, Double priceFrom, Double priceTo, Integer dateIn, Integer dateTo) throws PersonalException {
        String sql = createSql(type, genre, priceFrom, priceTo, dateIn, dateTo);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            int count = 1;
            if (type != 0) {
                statement.setInt(count, type);
                count++;
            }
            if (genre != 0) {
                statement.setInt(count, genre);
                count++;
            }
            if (priceFrom != 0) {
                statement.setDouble(count, priceFrom);
                count++;
            }
            if (priceTo != 0) {
                statement.setDouble(count, priceTo);
                count++;
            }
            if (dateIn != 0) {
                statement.setInt(count, dateIn);
                count++;
            }
            if (dateTo != 0) {
                statement.setInt(count, dateTo);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Disk> resultList = new ArrayList<>();
                Disk disk = null;
                if (resultSet.next()) {
                    disk = new Disk();
                    disk.setIdEntity(resultSet.getInt("id_disk"));
                    disk.setNameDisk(resultSet.getString("name"));
                    disk.setImage(resultSet.getString("image"));
                    disk.setPrice(resultSet.getDouble("price"));
                    disk.setTimeAdded(resultSet.getTimestamp("time_added"));
                    resultList.add(disk);
                }
                return resultList;
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    private String createSql(Integer type, Integer genre, Double priceFrom, Double priceTo, Integer dateIn, Integer dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GET_DISK_BY_PARAMETER);
        int count = 0;
        if (type != 0) {
            stringBuilder.append("  `type_id`=?");
            count++;
        }
        if (genre != 0) {
            if (count == 1) {
                stringBuilder.append(" AND ");
            }
            stringBuilder.append("  `genre_id`=?");
            count++;
        }
        if (priceFrom != 0) {
            if (count == 1) {
                stringBuilder.append(" AND ");
            }
            stringBuilder.append("  `price`>=? ");
            count++;
        }
        if (priceTo != 0) {
            if (count == 1) {
                stringBuilder.append(" AND ");
            }
            stringBuilder.append("  `price`<=?");
            count++;
        }
        if (dateIn != 0) {
            if (count == 1) {
                stringBuilder.append(" AND ");
            }
            stringBuilder.append("  `year`>=?");
            count++;
        }
        if (dateTo != 0) {
            if (count == 1) {
                stringBuilder.append(" AND ");
            }
            stringBuilder.append(" `year`<=?");
        }
        return stringBuilder.toString();
    }

    @Override
    public List<Disk> read() throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Disk> resultList = new ArrayList<>();
                Disk disk = null;
                if (resultSet.next()) {
                    disk = new Disk();
                    disk.setIdEntity(resultSet.getInt("id_disk"));
                    disk.setNameDisk(resultSet.getString("name"));
                    disk.setImage(resultSet.getString("image"));
                    disk.setPrice(resultSet.getDouble("price"));
                    disk.setTimeAdded(resultSet.getTimestamp("time_added"));
                    resultList.add(disk);
                }
                return resultList;
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }

    }
}

