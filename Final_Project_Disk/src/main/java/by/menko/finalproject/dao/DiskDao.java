package by.menko.finalproject.dao;


import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.Film;
import by.menko.finalproject.entity.Game;
import by.menko.finalproject.entity.Music;
import by.menko.finalproject.exception.PersonalException;

import java.util.List;
import java.util.Optional;

public interface DiskDao extends Dao<Disk> {
    void createDisk(Film disk) throws PersonalException;

    Integer create(Disk disk, Integer idGenre) throws PersonalException;

    void createDisk(Game disk) throws PersonalException;

    void createDisk(Music disk) throws PersonalException;

    List<Disk> readDiskByParameter(Integer type, Integer genre, Double priceFrom, Double priceTo, Integer dateIn, Integer dateTo) throws PersonalException;

    List<Disk> read() throws PersonalException;

    List<Disk> readByIdUser(Integer idUser) throws PersonalException;

    Optional<Disk> readFilm(Integer identity,Integer flagBlocked) throws PersonalException;

    Optional<Disk> readGame(Integer identity,Integer flagBlocked) throws PersonalException;

    Optional<Disk> readMusic(Integer identity,Integer flagBlocked) throws PersonalException;

    void updateFilm(Disk disk) throws PersonalException;

    void updateGame(Disk disk) throws PersonalException;

    void updateMusic(Disk disk) throws PersonalException;

    Optional<Disk> readByIdDisk(Integer idDisk) throws PersonalException;

    void blocked(Integer idEntity) throws PersonalException;

    void unLock(Integer idEntity) throws PersonalException;

    Optional<Disk> readForAdmin(Integer idDisk) throws PersonalException;

}
