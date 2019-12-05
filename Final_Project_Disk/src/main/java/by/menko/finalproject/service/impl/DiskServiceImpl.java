package by.menko.finalproject.service.impl;


import by.menko.finalproject.dao.CatalogDao;
import by.menko.finalproject.dao.DiskDao;
import by.menko.finalproject.entity.*;
import by.menko.finalproject.entity.enumtype.TypeDisk;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;
import by.menko.finalproject.service.DiskService;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class DiskServiceImpl extends ServiceImpl implements DiskService {
    private final static String NO_IMAGE = "images/no.png";
    private final static String PATH = "images/";

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

    @Override
    public void writeDisk(Disk disk, Part image, String pathTemp, Integer idUser) throws ServicePersonalException, PersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        CatalogDao catalog = transaction.createDao(TypeServiceAndDao.CATALOG);
        String fileName = image.getSubmittedFileName();
        if (!fileName.isEmpty()) {
            createDirAndWriteToFile(pathTemp, image);
            disk.setImage(PATH + fileName);
        } else {
            disk.setImage(NO_IMAGE);
        }
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

    }

    @Override
    public List<Disk> getAllDiskByIdUser(Integer idUser) throws PersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        return dao.readByIdUser(idUser);
    }

    @Override
    public Disk getDisk(String diskId) throws PersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        Integer id;
        try {
            id = Integer.parseInt(diskId);
        } catch (NumberFormatException e) {
            throw new PersonalException();
        }
        Optional<Disk> disk = dao.read(id);
        if (disk.isPresent()) {
            if (disk.get().getType().equals(TypeDisk.FILM.getName())) {
                disk = dao.readFilm(id);
            } else if (disk.get().getType().equals(TypeDisk.GAME.getName())) {
                disk = dao.readGame(id);
            } else if (disk.get().getType().equals(TypeDisk.MUSIC.getName())) {
                disk = dao.readMusic(id);
            }
            disk.get().setIdEntity(id);
        } else {
            throw new PersonalException();
        }
        return disk.get();

    }

    @Override
    public void updateDisk(Disk disk, Part image, String pathTemp) throws PersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        Disk diskOld = getDisk(String.valueOf(disk.getIdEntity()));
        if (disk.getImage() != null && !disk.getImage().isEmpty()
                && !diskOld.getImage().equals(disk.getImage())) {
            createDirAndWriteToFile(pathTemp, image);
            String fileName = PATH + image.getSubmittedFileName();
            disk.setImage(fileName);
        } else {
            disk.setImage(diskOld.getImage());
        }
        if (disk.getType().equals(TypeDisk.FILM.getName())) {
            dao.updateFilm(disk);
        } else if (disk.getType().equals(TypeDisk.GAME.getName())) {
            dao.updateGame(disk);
        } else if (disk.getType().equals(TypeDisk.MUSIC.getName())) {
            dao.updateMusic(disk);
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

    private void createDirAndWriteToFile(final String pathTemp,
                                         final Part filePart) throws PersonalException {
        try {
            File uploadDir = new File(pathTemp);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileName = filePart.getSubmittedFileName();
            File f = new File(pathTemp, fileName);
            BufferedImage image = null;

            image = ImageIO.read(filePart.getInputStream());

            String format = fileName.substring(fileName.lastIndexOf('.') + 1);
            ImageIO.write(image, format, f);
        } catch (IOException e) {
            throw new PersonalException();
        }
    }
}
