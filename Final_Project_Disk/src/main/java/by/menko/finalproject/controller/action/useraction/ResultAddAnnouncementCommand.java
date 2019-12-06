package by.menko.finalproject.controller.action.useraction;

import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;
import by.menko.finalproject.service.DiskService;
import by.menko.finalproject.service.validator.DiskValidator;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResultAddAnnouncementCommand extends Command {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        DiskService service = factory.createService(TypeServiceAndDao.DISK);
        DiskValidator validator = new DiskValidator();
        Part image = request.getPart("image");
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        String path = request.getServletContext().getResource("")
                .getPath();
        String pathTemp = path + request.getServletContext()
                .getInitParameter("images.dir") + "/";
        try {
            Disk disk = validator.validate(request);
            service.writeDisk(disk, image, pathTemp, user.getIdEntity());
        } catch (ServicePersonalException e) {
            Map<String, String> message = new HashMap<>();
            message.put(e.getMessage(), e.getMessage());
            String json = new Gson().toJson(message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}