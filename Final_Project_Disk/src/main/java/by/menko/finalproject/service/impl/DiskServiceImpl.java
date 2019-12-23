package by.menko.finalproject.service.impl;


import by.menko.finalproject.dao.CatalogDao;
import by.menko.finalproject.dao.DiskDao;
import by.menko.finalproject.entity.*;
import by.menko.finalproject.entity.enumtype.Role;
import by.menko.finalproject.entity.enumtype.TypeDisk;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.exception.ServicePersonalException;
import by.menko.finalproject.service.DiskService;
import by.menko.finalproject.service.validator.DiskValidator;

import java.util.List;
import java.util.Optional;


public class DiskServiceImpl extends ServiceImpl implements DiskService {


    @Override
    public List<Disk> getDisk(final String type, final String genre,
                              final String priceFrom, final String priceTo,
                              final String dateIn, String dateTo)
            throws ServicePersonalException, PersonalException {
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
        boolean flag = typeDisk == 0 && genreDisk == 0 && priceFromDisk == 0
                && priceToDisk == 0 && yearIn == 0 && yearTo == 0;
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        List<Disk> findDisk;
        try {
            if (flag) {
                findDisk = dao.read();
            } else if (validPriceAndYear(priceFromDisk, priceToDisk, yearIn, yearTo)) {
                findDisk = dao.readDiskByParameter(typeDisk, genreDisk,
                        priceFromDisk, priceToDisk, yearIn, yearTo);
            } else {
                throw new ServicePersonalException("Incorrect value.");
            }
            transaction.commit();
            return findDisk;
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }


    @Override
    public void writeDisk(final Disk disk, final Integer idUser) throws PersonalException, ServicePersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        CatalogDao catalog = transaction.createDao(TypeServiceAndDao.CATALOG);
        DiskValidator validator = new DiskValidator();
        validator.validate(disk);
        try {
            disk.setIdUser(idUser);
            Optional<Integer> idGenre = catalog.read(disk.getGenre(), disk.getType());
            if (idGenre.isPresent()) {
                Integer idDisk = dao.create(disk, idGenre.get());
                disk.setIdEntity(idDisk);
            } else {
                throw new PersonalException();
            }
            if (disk.getType().equals(TypeDisk.FILM.getName())) {
                dao.createDisk(((Film) disk));
            } else if (disk.getType().equals(TypeDisk.GAME.getName())) {
                dao.createDisk(((Game) disk));
            } else {
                dao.createDisk(((Music) disk));
            }
            transaction.commit();
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }

    @Override
    public List<Disk> getAllDiskByIdUser(final Integer idUser) throws PersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        try {
            List<Disk> listAllDisk = dao.readByIdUser(idUser);
            transaction.commit();
            return listAllDisk;
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }

    @Override
    public Disk getDisk(final String diskId, final UserInfo user) throws PersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        Integer id;
        int flagBlocked;
        if (user != null && user.getRole() == Role.ADMINISTRATOR) {
            flagBlocked = 1;
        } else {
            flagBlocked = 0;
        }
        try {
            id = Integer.parseInt(diskId);
        } catch (NumberFormatException e) {
            throw new PersonalException();
        }
        try {
            Optional<Disk> disk = dao.read(id);
            if (disk.isPresent()) {
                if (disk.get().getType().equals(TypeDisk.FILM.getName())) {
                    disk = dao.readFilm(id, flagBlocked);
                } else if (disk.get().getType().equals(TypeDisk.GAME.getName())) {
                    disk = dao.readGame(id, flagBlocked);
                } else if (disk.get().getType().equals(TypeDisk.MUSIC.getName())) {
                    disk = dao.readMusic(id, flagBlocked);
                }
                if (disk.isPresent()) {
                    disk.get().setIdEntity(id);
                } else {
                    throw new PersonalException("Disk is null");
                }
            } else {
                throw new PersonalException("Disk is null");
            }
            transaction.commit();
            return disk.get();
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }

    @Override
    public void updateDisk(final Disk disk, final UserInfo user) throws PersonalException,
            ServicePersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        DiskValidator validator = new DiskValidator();
        validator.validate(disk);
        try {
            Disk diskOld = getDisk(String.valueOf(disk.getIdEntity()), user);
            if (disk.getImage().isEmpty()) {
                disk.setImage(diskOld.getImage());
            }
            if (disk.getType().equals(TypeDisk.FILM.getName())) {
                dao.updateFilm(disk);
            } else if (disk.getType().equals(TypeDisk.GAME.getName())) {
                dao.updateGame(disk);
            } else if (disk.getType().equals(TypeDisk.MUSIC.getName())) {
                dao.updateMusic(disk);
            }
            transaction.commit();
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }

    @Override
    public void deleteDisk(final String idDisk, final Integer idUser) throws PersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        try {
            Integer diskId = Integer.parseInt(idDisk);
            dao.delete(diskId, idUser);
            transaction.commit();
        } catch (NumberFormatException | PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }

    private boolean validPriceAndYear(final Double priceFrom, final Double priceTo,
                                      final Integer yearIn, final Integer yearTo) {
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
