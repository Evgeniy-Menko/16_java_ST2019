package by.menko.finalproject.dao;


import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.Film;
import by.menko.finalproject.entity.Game;
import by.menko.finalproject.entity.Music;
import by.menko.finalproject.exception.PersonalException;

public interface DiskDao extends Dao<Disk> {
    void createDisk(Film disk) throws PersonalException;

    void createDisk(Game disk) throws PersonalException;

    void createDisk(Music disk) throws PersonalException;

    void createImage(Disk disk) throws PersonalException;
}
