package by.menko.finalproject.service.validator;

import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.Film;

import by.menko.finalproject.entity.Game;
import by.menko.finalproject.entity.Music;
import by.menko.finalproject.entity.enumtype.TypeDisk;
import by.menko.finalproject.exception.ServicePersonalException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

public class DiskValidator {
    private final static String REGEX = "[A-zА-яЁё]*";
    private final static String REGEX_SENTENCE = "^(?!\\s*$)[A-zА-яЁё0-9\\S_ ]*$";
    private final static String REGEX_TIME_RUNNING = "[0-9:0-9]{3,5}";

    public Disk validate(HttpServletRequest request) throws IOException, ServletException, ServicePersonalException {
        Disk disk = null;
        Part image = request.getPart("image");
        String imageName = image.getSubmittedFileName();
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String genre = request.getParameter("genre");
        String price = request.getParameter("price");
        String year = request.getParameter("year");
        String comment = request.getParameter("comment");
        String country = request.getParameter("country");
        String time = request.getParameter("time");
        String age = request.getParameter("age");
        String developer = request.getParameter("developer");
        String singer = request.getParameter("singer");
        String albom = request.getParameter("albom");
        String formatImage = imageName.substring(imageName.lastIndexOf('.') + 1);

        if (type.equals(TypeDisk.FILM.getName())) {

            disk = new Film();
            if (country != null && !country.isEmpty() && country.matches(REGEX)) {
                ((Film) disk).setCountry(country);
            } else if (country != null && !country.isEmpty()) {
                throw new ServicePersonalException("errorCountry");
            }
            if (time != null && !time.isEmpty() && time.matches(REGEX_TIME_RUNNING)) {
                ((Film) disk).setRunningTime(time);
            } else if (time != null && !time.isEmpty()) {
                throw new ServicePersonalException("errorTime");
            }
        } else if (type.equals(TypeDisk.GAME.getName())) {
            disk = new Game();
            try {
                if (age != null && !age.isEmpty()) {
                    ((Game) disk).setAgeLimit(Integer.parseInt(age));
                }
            } catch (NumberFormatException e) {
                throw new ServicePersonalException("errorAge");
            }
            if (developer != null && !developer.isEmpty()) {
                ((Game) disk).setDeveloper(developer);
            }
        } else {
            disk = new Music();
            if (singer != null && !singer.isEmpty()) {
                ((Music) disk).setSinger(singer);
            }
            if (albom != null && !albom.isEmpty()) {
                ((Music) disk).setAlbom(albom);
            }
        }
        disk.setType(type);

        boolean flag = "jpg".equals(formatImage) || "png".equals(formatImage) || "jpeg".equals(formatImage) || "gif".equals(formatImage);

        if (!imageName.isEmpty() && flag) {
            disk.setImage(imageName);
        } else if (!imageName.isEmpty()) {
            throw new ServicePersonalException("errorFormatImage");
        }

        if (name != null && !name.isEmpty() && name.matches(REGEX_SENTENCE)) {
            disk.setNameDisk(name);
        } else {
            throw new ServicePersonalException("errorNameDisk");
        }
        try {
            if (price != null && !price.isEmpty()) {
                disk.setPrice(Double.parseDouble(price));
            } else {
                throw new ServicePersonalException("errorRequired");
            }
            if (year != null && !year.isEmpty()) {
                disk.setYear(Integer.parseInt(year));
            }

        } catch (NumberFormatException e) {
            throw new ServicePersonalException("incorrectNumber");
        }
        if (comment != null && !comment.isEmpty()) {
            disk.setDescription(comment);
        }

            disk.setGenre(genre);

        return disk;
    }
}
