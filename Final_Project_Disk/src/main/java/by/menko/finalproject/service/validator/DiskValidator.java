package by.menko.finalproject.service.validator;

import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.Film;

import by.menko.finalproject.entity.Game;
import by.menko.finalproject.entity.Music;
import by.menko.finalproject.entity.enumtype.TypeDisk;
import by.menko.finalproject.service.exception.ServicePersonalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

public class DiskValidator {
    private static Logger logger = LogManager.getLogger();
    private static final int MAX_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    private static final int MIN_YEAR = 1970;
    private static final int MIN_NUMBER = 0;
    private static final int MAX_AGE = 18;
    private static final String REGEX_NAME = "[A-zА-яЁё]*";
    private static final String REGEX_SENTENCE = "^(?!\\s*$)[A-zА-яЁё0-9,.!@#?:()_ ]*$";
    private static final String REGEX_TIME_RUNNING = "[0-9:0-9]{3,5}";
    private static final String REGEX_COMMENT = "^(?!\\s\\t\\n\\r*$)[A-zА-яЁё0-9,.!@#?:()_\\t\\n\\r ]*$";

    public void validate(final Disk disk) throws ServicePersonalException {
        if (disk.getType().equals(TypeDisk.FILM.getName())) {
            if (((Film) disk).getCountry() != null && !((Film) disk).getCountry().isEmpty()
                    && !((Film) disk).getCountry().matches(REGEX_NAME)) {
                throw new ServicePersonalException("errorCountry");
            }
            if (((Film) disk).getRunningTime() != null && !((Film) disk).getRunningTime().isEmpty()
                    && !((Film) disk).getRunningTime().matches(REGEX_TIME_RUNNING)) {
                throw new ServicePersonalException("errorTime");
            }
        } else if (disk.getType().equals(TypeDisk.GAME.getName())) {
            if (((Game) disk).getAgeLimit() < MIN_NUMBER && ((Game) disk).getAgeLimit() > MAX_AGE) {
                throw new ServicePersonalException("errorAge");
            }
            if (((Game) disk).getDeveloper() != null && !((Game) disk).getDeveloper().isEmpty()
                    && !((Game) disk).getDeveloper().matches(REGEX_SENTENCE)) {
                throw new ServicePersonalException("errorDeveloper");
            }
        } else {
            if (((Music) disk).getSinger() != null && !((Music) disk).getSinger().isEmpty()
                    && !((Music) disk).getSinger().matches(REGEX_NAME)) {
                throw new ServicePersonalException("errorSinger");
            }
            if (((Music) disk).getAlbom() != null && !((Music) disk).getAlbom().isEmpty()
                    && !((Music) disk).getAlbom().matches(REGEX_NAME)) {
                throw new ServicePersonalException("errorAlbom");
            }
        }
        if (disk.getNameDisk() == null || disk.getNameDisk().isEmpty()
                || !disk.getNameDisk().matches(REGEX_SENTENCE)) {
            throw new ServicePersonalException("errorNameDisk");
        }
        if (disk.getPrice() < MIN_NUMBER) {
            throw new ServicePersonalException("errorPrice");
        }
        if (disk.getYear() != MIN_NUMBER && disk.getYear() < MIN_YEAR || disk.getYear() > MAX_YEAR) {
            throw new ServicePersonalException("errorYear");
        }
        if (disk.getDescription() != null && !disk.getDescription().isEmpty()
                && !disk.getDescription().matches(REGEX_COMMENT)) {
            throw new ServicePersonalException("incorrectComment");
        }
    }
}
