package by.menko.finalproject.service;


import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;


import java.util.List;

public interface DiskService extends Service {
    List<Disk> getDisk(String type, String genre, String priceFrom, String priceTo, String dateIn, String dateTo) throws ServicePersonalException, PersonalException;

    void writeDisk(Disk disk, Integer idUser) throws ServicePersonalException, PersonalException;

    List<Disk> getAllDiskByIdUser(Integer idUser) throws PersonalException;

    Disk getDisk(String diskId, UserInfo user) throws PersonalException;

    void updateDisk(Disk disk, UserInfo user) throws PersonalException;

    void deleteDisk(String idDisk, Integer idUser) throws PersonalException;


}
