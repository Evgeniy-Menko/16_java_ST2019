package by.menko.finalproject.service;


import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.exception.ServicePersonalException;


import java.util.List;

public interface DiskService extends Service {
    List<Disk> getDisk(final String type, final String genre, final String priceFrom,
                       final String priceTo, final String dateIn, final String dateTo)
            throws ServicePersonalException, PersonalException;

    void writeDisk(final Disk disk, final Integer idUser) throws ServicePersonalException, PersonalException;

    List<Disk> getAllDiskByIdUser(final Integer idUser) throws PersonalException;

    Disk getDisk(final String diskId, final UserInfo user) throws PersonalException;

    void updateDisk(final Disk disk, final UserInfo user) throws PersonalException;

    void deleteDisk(final String idDisk, final Integer idUser) throws PersonalException;


}
