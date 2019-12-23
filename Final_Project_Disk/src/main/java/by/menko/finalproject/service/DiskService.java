package by.menko.finalproject.service;


import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.exception.ServicePersonalException;


import java.util.List;

public interface DiskService extends Service {
    /**
     * get list disks by parameter for search.
     * @param type type disk.
     * @param genre genre disk.
     * @param priceFrom price from.
     * @param priceTo price to.
     * @param dateIn year in.
     * @param dateTo year to.
     * @return list with disks that found
     * @throws ServicePersonalException no validate parameter.
     * @throws PersonalException sql exception.
     */
    List<Disk> getDisk(final String type, final String genre, final String priceFrom,
                       final String priceTo, final String dateIn, final String dateTo)
            throws ServicePersonalException, PersonalException;

    /**
     * create disk.
     * @param disk object disk.
     * @param idUser id user.
     * @throws ServicePersonalException no validate parameter disk.
     * @throws PersonalException sql exception.
     */
    void writeDisk(final Disk disk, final Integer idUser) throws ServicePersonalException, PersonalException;

    /**
     * Get disk by id user for shopping cart.
     * @param idUser id user.
     * @return list with  disks
     * @throws PersonalException sql exception.
     */
    List<Disk> getAllDiskByIdUser(final Integer idUser) throws PersonalException;

    /**
     * get disk by id user and id disk.
     * @param diskId id disk.
     * @param user id user.
     * @return disk that found
     * @throws PersonalException sql exception.
     */
    Disk getDisk(final String diskId, final UserInfo user) throws PersonalException;

    /**
     * Update disk new disk by id user
     * @param disk new parameter's disk.
     * @param user user who updated announcement
     * @throws PersonalException sql exception.
     * @throws ServicePersonalException no validate parameter disk.
     */
    void updateDisk(final Disk disk, final UserInfo user) throws PersonalException, ServicePersonalException;

    /**
     * Delete disk.
     * @param idDisk id disk.
     * @param idUser id user.
     * @throws PersonalException sql exception or number format id disk.
     */
    void deleteDisk(final String idDisk, final Integer idUser) throws PersonalException;


}
