package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.*;
import by.menko.finalproject.entity.enumtype.TypeDisk;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.exception.ServicePersonalException;
import by.menko.finalproject.service.DiskService;
import by.menko.finalproject.service.FileService;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AnnouncementEditResultCommand extends UserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        DiskService service = factory.createService(TypeServiceAndDao.DISK);
        FileService fileService = factory.createService(TypeServiceAndDao.FILE);
        Map<String, String> messages = new HashMap<>();
        try {
            Disk disk = getDisk(request);
            UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
            String idDisk = request.getParameter("id");
            String path = request.getServletContext().getResource("")
                    .getPath();
            String pathTemp = path + request.getServletContext()
                    .getInitParameter("images.dir") + "/";
            String pathImage = fileService.createDirAndWriteToFile(pathTemp, request.getPart("image"), true);
            disk.setIdEntity(Integer.parseInt(idDisk));
            disk.setImage(pathImage);
            service.updateDisk(disk, user);
            String message = String.format("User %d update announcement %s", user.getIdEntity(), idDisk);
            logger.info(message);
            messages.put("url", request.getContextPath() + ConstantsPath.MY_ANNOUNCEMENT);
            String json = new Gson().toJson(message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (ServicePersonalException e) {
            logger.info("Incorrect value", e);
            messages.put(e.getMessage(), e.getMessage());
            String json = new Gson().toJson(messages);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    /**
     * Gets user data from request parameters.
     *
     * @param request the provided request
     *
     * @return the disk entity.
     */
    private Disk getDisk(final HttpServletRequest request) throws IOException, ServletException, ServicePersonalException {
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
        Disk disk;
        if (type.equals(TypeDisk.FILM.getName())) {
            disk = new Film();
            ((Film) disk).setCountry(country);
            ((Film) disk).setRunningTime(time);
        } else if (type.equals(TypeDisk.GAME.getName())) {
            disk = new Game();
            try {
                ((Game) disk).setAgeLimit(Integer.parseInt(age));
            } catch (NumberFormatException e) {
                logger.debug(String.format("Incorrect age %s", age));
                throw new ServicePersonalException("errorAge");
            }
            ((Game) disk).setDeveloper(developer);
        } else {
            disk = new Music();
            ((Music) disk).setSinger(singer);
            ((Music) disk).setAlbom(albom);
        }
        disk.setImage(imageName);
        disk.setType(type);
        disk.setNameDisk(name);
        disk.setDescription(comment);
        try {
            disk.setPrice(Double.parseDouble(price));
            if (year != null && !year.isEmpty()) {
                disk.setYear(Integer.parseInt(year));
            }
        } catch (NumberFormatException e) {
            logger.debug("Incorrect number format");
            throw new ServicePersonalException("incorrectNumber");
        }
        disk.setGenre(genre);
        return disk;
    }
}
