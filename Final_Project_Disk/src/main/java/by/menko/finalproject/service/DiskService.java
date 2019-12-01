package by.menko.finalproject.service;


import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;

import java.util.List;

public interface DiskService extends Service {
    List<Disk> getDisk(String type, String genre, String priceFrom, String priceTo, String dateIn, String dateTo) throws ServicePersonalException, PersonalException;
}
