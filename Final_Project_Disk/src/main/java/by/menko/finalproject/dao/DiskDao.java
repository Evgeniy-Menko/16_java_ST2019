package by.menko.finalproject.dao;


import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.Film;
import by.menko.finalproject.entity.Game;
import by.menko.finalproject.entity.Music;
import by.menko.finalproject.dao.exception.PersonalException;

import java.util.List;
import java.util.Optional;

public interface DiskDao extends Dao<Disk> {
    void createDisk(final Film disk) throws PersonalException;

    Integer create(final Disk disk, final Integer idGenre) throws PersonalException;

    void createDisk(final Game disk) throws PersonalException;

    void createDisk(final Music disk) throws PersonalException;

    List<Disk> readDiskByParameter(final Integer type, final Integer genre,
                                   final Double priceFrom, final Double priceTo,
                                   final Integer dateIn, final Integer dateTo)
            throws PersonalException;

    List<Disk> read() throws PersonalException;

    List<Disk> readByIdUser(final Integer idUser) throws PersonalException;

    Optional<Disk> readFilm(final Integer identity, final Integer flagBlocked) throws PersonalException;

    Optional<Disk> readGame(final Integer identity, final Integer flagBlocked) throws PersonalException;

    Optional<Disk> readMusic(final Integer identity, final Integer flagBlocked) throws PersonalException;

    void updateFilm(final Disk disk) throws PersonalException;

    void updateGame(final Disk disk) throws PersonalException;

    void updateMusic(final Disk disk) throws PersonalException;

    Optional<Disk> readByIdDisk(final Integer idDisk) throws PersonalException;

    void blocked(final Integer idEntity) throws PersonalException;

    void unLock(final Integer idEntity) throws PersonalException;

    Optional<Disk> readForAdmin(final Integer idDisk) throws PersonalException;

}
