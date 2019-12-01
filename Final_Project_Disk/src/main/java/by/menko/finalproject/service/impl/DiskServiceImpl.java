package by.menko.finalproject.service.impl;


import by.menko.finalproject.dao.DiskDao;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;
import by.menko.finalproject.service.DiskService;

import java.util.List;


public class DiskServiceImpl extends ServiceImpl implements DiskService {


    @Override
    public List<Disk> getDisk(String type, String genre, String priceFrom, String priceTo, String dateIn, String dateTo) throws ServicePersonalException, PersonalException {
        int typeDisk = 0;
        int genreDisk = 0;
        double priceFromDisk = 0;
        double priceToDisk = 0;
        int yearIn = 0;
        int yearTo = 0;
        try {
            if (type != null && !type.isEmpty()) {
                typeDisk = Integer.parseInt(type);
            }
            if (genre != null && !genre.isEmpty()) {
                genreDisk = Integer.parseInt(genre);
            }
            if (priceFrom != null && !priceFrom.isEmpty()) {
                priceFromDisk = Double.parseDouble(priceFrom);
            }
            if (priceTo != null && !priceTo.isEmpty()) {
                priceToDisk = Double.parseDouble(priceTo);
            }
            if (dateIn != null && !dateIn.isEmpty()) {
                yearIn = Integer.parseInt(dateIn);
            }
            if (dateTo != null && !dateTo.isEmpty()) {
                yearTo = Integer.parseInt(dateTo);
            }
        } catch (NumberFormatException e) {
            throw new ServicePersonalException(e);
        }
        boolean flag = typeDisk == 0 && genreDisk == 0 && priceFromDisk == 0 && priceToDisk == 0 && yearIn == 0 && yearTo == 0;
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        if (flag) {
            return dao.read();
        } else if (validPriceAndYear(priceFromDisk, priceToDisk, yearIn, yearTo)) {
            return dao.readDiskByParameter(typeDisk, genreDisk, priceFromDisk, priceToDisk, yearIn, yearTo);
        } else {
            throw new ServicePersonalException("Incorrect value.");
        }
    }

    private boolean validPriceAndYear(Double priceFrom, Double priceTo, Integer yearIn, Integer yearTo) {
        boolean flag = true;
        if (priceTo != 0 && priceFrom > priceTo) {
            flag = false;
        }
        if (yearTo != 0 && yearIn > yearTo) {
            flag = false;
        }
        return flag;
    }
}
