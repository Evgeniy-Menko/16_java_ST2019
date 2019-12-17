package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.exception.ServicePersonalException;
import by.menko.finalproject.service.DiskService;
import by.menko.finalproject.service.FileService;
import by.menko.finalproject.service.validator.DiskValidator;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AnnouncementEditResultCommand extends UserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        DiskService service = factory.createService(TypeServiceAndDao.DISK);
        FileService fileService = factory.createService(TypeServiceAndDao.FILE);
        DiskValidator validator = new DiskValidator();
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        String idDisk = request.getParameter("id");
        String path = request.getServletContext().getResource("")
                .getPath();
        String pathTemp = path + request.getServletContext()
                .getInitParameter("images.dir") + "/";
        String pathImage = fileService.createDirAndWriteToFile(pathTemp, request.getPart("image"));
        try {
            Disk disk = validator.validate(request);
            disk.setIdEntity(Integer.parseInt(idDisk));
            disk.setImage(pathImage);
            service.updateDisk(disk, user);
            logger.info(String.format("User %d update announcement %s", user.getIdEntity(), idDisk));
        } catch (ServicePersonalException e) {
            logger.info("Incorrect value", e);
            Map<String, String> message = new HashMap<>();
            message.put(e.getMessage(), e.getMessage());
            String json = new Gson().toJson(message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}
