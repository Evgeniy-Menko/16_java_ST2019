package by.menko.finalproject.dao;


import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.Film;
import by.menko.finalproject.entity.Game;
import by.menko.finalproject.entity.Music;
import by.menko.finalproject.exception.PersonalException;

import java.util.List;

public interface DiskDao extends Dao<Disk> {
    void createDisk(Film disk) throws PersonalException;

    void createDisk(Game disk) throws PersonalException;

    void createDisk(Music disk) throws PersonalException;

    void createImage(Disk disk) throws PersonalException;

    List<Disk> readDiskByParameter(Integer type, Integer genre, Double priceFrom, Double priceTo, Integer dateIn, Integer dateTo) throws PersonalException;

    List<Disk> read() throws PersonalException;
}
