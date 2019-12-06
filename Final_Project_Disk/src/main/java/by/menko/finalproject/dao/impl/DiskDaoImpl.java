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
    private static final String CREATE_FILM = "INSERT INTO `disk_info_films` (`disk_id`, `country`, `running_time`) VALUES (?, ?, ?)";
    private static final String CREATE_GAME = "INSERT INTO `disk_info_game` (`disk_id`,  `age_limit`,`developer`) VALUES (?, ?, ?)";
    private static final String CREATE_MUSIC = "INSERT INTO `disk_info_music` (`disk_id`, `singer`,`albom`) VALUES (?, ?, ?)";

    private static final String CREATE_DISK = "INSERT INTO `disk` (`user_id`, `name`, `genre_id`,`price`,`description`,`year`,`image`) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_DISK_BY_PARAMETER = "SELECT `id_disk`,`name`,`image`,`price`,`time_added` FROM `disk` INNER JOIN `genre` ON `genre_id` = `id_genre`  WHERE  ";
    private static final String GET_ALL = "SELECT `id_disk`,`name`,`image`,`price`,`time_added` FROM `disk` ORDER BY `time_added` DESC";

    @Override
    public Integer create(Disk disk, Integer idGenre) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_DISK, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, disk.getIdUser());
            statement.setString(2, disk.getNameDisk());
            statement.setInt(3, idGenre);
            statement.setDouble(4, disk.getPrice());
            statement.setString(5, disk.getDescription());
            statement.setInt(6, disk.getYear());
            statement.setString(7, disk.getImage());
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
    public void createDisk(Music disk) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_MUSIC)) {
            statement.setInt(1, disk.getIdEntity());
            statement.setString(2, disk.getSinger());
            statement.setString(3, disk.getAlbom());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    @Override
    public void createDisk(Film disk) throws PersonalException {


        try (PreparedStatement statement = connection.prepareStatement(CREATE_FILM)) {

            statement.setInt(1, disk.getIdEntity());
            statement.setString(2, disk.getCountry());
            statement.setString(3, disk.getRunningTime());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        }

    }

    @Override
    public void createDisk(Game disk) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_GAME)) {
            statement.setInt(1, disk.getIdEntity());
            statement.setInt(2, disk.getAgeLimit());
            statement.setString(3, disk.getDeveloper());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
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
                while (resultSet.next()) {
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
        stringBuilder.append("ORDER BY `time_added` DESC");
        return stringBuilder.toString();
    }

    @Override
    public List<Disk> read() throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Disk> resultList = new ArrayList<>();
                Disk disk = null;
                while (resultSet.next()) {
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

    private static final String GET_BY_ID_USER = "SELECT `id_disk`,`name`,`image`,`price`,`time_added` FROM `disk` WHERE `user_id`=? ORDER BY `time_added` DESC";

    @Override
    public List<Disk> readByIdUser(Integer idUser) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_USER)) {
            statement.setInt(1, idUser);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Disk> resultList = new ArrayList<>();
                Disk disk = null;
                while (resultSet.next()) {
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

    private static final String GET_BY_ID = "SELECT `type`,`user_id` FROM `disk` INNER JOIN `genre` ON `genre_id` = `id_genre` INNER JOIN `type` ON `type_id` = `id_type` WHERE  `id_disk`=?";

    @Override
    public Optional<Disk> read(Integer identity) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, identity);
            try (ResultSet resultSet = statement.executeQuery()) {
                Disk disk = null;
                if (resultSet.next()) {
                    disk = new Disk();
                    disk.setType(resultSet.getString("type"));
                    disk.setIdUser(resultSet.getInt("user_id"));
                }
                return Optional.ofNullable(disk);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    private static final String GET_BY_ID_DISK = "SELECT `user_id`,`name`,`price`,`description`,`year`,`image`,`time_added` FROM `disk`  WHERE  `id_disk`=?";

    @Override
    public Optional<Disk> readByIdDisk(Integer idDisk) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_DISK)) {
            statement.setInt(1, idDisk);
            try (ResultSet resultSet = statement.executeQuery()) {
                Disk disk = null;
                if (resultSet.next()) {
                    disk = new Disk();
                    disk.setIdUser(resultSet.getInt("user_id"));
                    disk.setNameDisk(resultSet.getString("name"));
                    disk.setPrice(resultSet.getDouble("price"));
                    disk.setDescription(resultSet.getString("description"));
                    disk.setYear(resultSet.getInt("year"));
                    disk.setImage(resultSet.getString("image"));
                    disk.setTimeAdded(resultSet.getTimestamp("time_added"));
                }
                return Optional.ofNullable(disk);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    private static final String GET_BY_ID_FILM = "SELECT `user_id`,`name`,`type`,`genre`,`price`,`description`,`year`,`image`,`time_added`,`country`,`running_time` FROM `disk` INNER JOIN `genre` ON `genre_id` = `id_genre` INNER JOIN `type` ON `type_id` = `id_type` INNER JOIN `disk_info_films` ON `id_disk` = `disk_id` WHERE  `id_disk`=?";

    @Override
    public Optional<Disk> readFilm(Integer identity) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_FILM)) {
            statement.setInt(1, identity);
            try (ResultSet resultSet = statement.executeQuery()) {
                Disk disk = null;
                if (resultSet.next()) {
                    disk = new Film();
                    disk.setIdUser(resultSet.getInt("user_id"));
                    disk.setNameDisk(resultSet.getString("name"));
                    disk.setType(resultSet.getString("type"));
                    disk.setGenre(resultSet.getString("genre"));
                    disk.setPrice(resultSet.getDouble("price"));
                    disk.setDescription(resultSet.getString("description"));
                    disk.setYear(resultSet.getInt("year"));
                    disk.setImage(resultSet.getString("image"));
                    disk.setTimeAdded(resultSet.getTimestamp("time_added"));
                    ((Film) disk).setCountry(resultSet.getString("country"));
                    ((Film) disk).setRunningTime(resultSet.getString("running_time"));
                }
                return Optional.ofNullable(disk);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    private static final String GET_BY_ID_GAME = "SELECT `user_id`,`name`,`type`,`genre`,`price`,`description`,`year`,`image`,`time_added`,`age_limit`,`developer` FROM `disk` INNER JOIN `genre` ON `genre_id` = `id_genre` INNER JOIN `type` ON `type_id` = `id_type` INNER JOIN `disk_info_game` ON `id_disk` = `disk_id` WHERE  `id_disk`=?";

    @Override
    public Optional<Disk> readGame(Integer identity) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_GAME)) {
            statement.setInt(1, identity);
            try (ResultSet resultSet = statement.executeQuery()) {
                Disk disk = null;
                if (resultSet.next()) {
                    disk = new Game();
                    disk.setIdUser(resultSet.getInt("user_id"));
                    disk.setNameDisk(resultSet.getString("name"));
                    disk.setType(resultSet.getString("type"));
                    disk.setGenre(resultSet.getString("genre"));
                    disk.setPrice(resultSet.getDouble("price"));
                    disk.setDescription(resultSet.getString("description"));
                    disk.setYear(resultSet.getInt("year"));
                    disk.setImage(resultSet.getString("image"));
                    disk.setTimeAdded(resultSet.getTimestamp("time_added"));
                    ((Game) disk).setAgeLimit(resultSet.getInt("age_limit"));
                    ((Game) disk).setDeveloper(resultSet.getString("developer"));
                }
                return Optional.ofNullable(disk);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    private static final String GET_BY_ID_MUSIC = "SELECT `user_id`,`name`,`type`,`genre`,`price`,`description`,`year`,`image`,`time_added`,`albom`,`singer` FROM `disk` INNER JOIN `genre` ON `genre_id` = `id_genre` INNER JOIN `type` ON `type_id` = `id_type` INNER JOIN `disk_info_music` ON `id_disk` = `disk_id` WHERE  `id_disk`=?";

    @Override
    public Optional<Disk> readMusic(Integer identity) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID_MUSIC)) {
            statement.setInt(1, identity);
            try (ResultSet resultSet = statement.executeQuery()) {
                Disk disk = null;
                if (resultSet.next()) {
                    disk = new Music();
                    disk.setIdUser(resultSet.getInt("user_id"));
                    disk.setNameDisk(resultSet.getString("name"));
                    disk.setType(resultSet.getString("type"));
                    disk.setGenre(resultSet.getString("genre"));
                    disk.setPrice(resultSet.getDouble("price"));
                    disk.setDescription(resultSet.getString("description"));
                    disk.setYear(resultSet.getInt("year"));
                    disk.setImage(resultSet.getString("image"));
                    disk.setTimeAdded(resultSet.getTimestamp("time_added"));
                    ((Music) disk).setAlbom(resultSet.getString("albom"));
                    ((Music) disk).setSinger(resultSet.getString("singer"));
                }
                return Optional.ofNullable(disk);
            } catch (SQLException e) {
                throw new PersonalException(e);
            }
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    private static final String UPDATE_FILM = "UPDATE `disk` INNER JOIN `disk_info_films`  ON `id_disk` = `disk_id` SET  `name`=?,`price`=?,`description`=?,`year`=?,`image`=?,`country`=?,`running_time`=? where `id_disk`=?";

    @Override
    public void updateFilm(Disk disk) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_FILM)) {
            statement.setString(1, disk.getNameDisk());
            statement.setDouble(2, disk.getPrice());
            statement.setString(3, disk.getDescription());
            statement.setInt(4, disk.getYear());
            statement.setString(5, disk.getImage());
            statement.setString(6, ((Film) disk).getCountry());
            statement.setString(7, ((Film) disk).getRunningTime());
            statement.setInt(8, disk.getIdEntity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    private static final String UPDATE_GAME = "UPDATE `disk` INNER JOIN `disk_info_game`  ON `id_disk` = `disk_id` SET  `name`=?,`price`=?,`description`=?,`year`=?,`image`=?,`age_limit`=?,`developer`=? where `id_disk`=?";

    @Override
    public void updateGame(Disk disk) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_GAME)) {
            statement.setString(1, disk.getNameDisk());
            statement.setDouble(2, disk.getPrice());
            statement.setString(3, disk.getDescription());
            statement.setInt(4, disk.getYear());
            statement.setString(5, disk.getImage());
            statement.setInt(6, ((Game) disk).getAgeLimit());
            statement.setString(7, ((Game) disk).getDeveloper());
            statement.setInt(8, disk.getIdEntity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }

    private static final String UPDATE_MUSIC = "UPDATE `disk` INNER JOIN `disk_info_music`  ON `id_disk` = `disk_id` SET  `name`=?,`price`=?,`description`=?,`year`=?,`image`=?,`albom`=?,`singer`=? where `id_disk`=?";

    @Override
    public void updateMusic(Disk disk) throws PersonalException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_MUSIC)) {
            statement.setString(1, disk.getNameDisk());
            statement.setDouble(2, disk.getPrice());
            statement.setString(3, disk.getDescription());
            statement.setInt(4, disk.getYear());
            statement.setString(5, disk.getImage());
            statement.setString(6, ((Music) disk).getAlbom());
            statement.setString(7, ((Music) disk).getSinger());
            statement.setInt(8, disk.getIdEntity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersonalException(e);
        }
    }


}

