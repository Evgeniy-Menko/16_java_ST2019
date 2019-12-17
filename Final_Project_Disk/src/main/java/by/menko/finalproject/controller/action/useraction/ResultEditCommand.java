package by.menko.finalproject.controller.action.useraction;

import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.exception.ServicePersonalException;
import by.menko.finalproject.service.FileService;
import by.menko.finalproject.service.UserService;
import by.menko.finalproject.service.validator.ProfileValidator;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResultEditCommand extends UserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        UserService service = factory.createService(TypeServiceAndDao.USER);
        FileService fileService = factory.createService(TypeServiceAndDao.FILE);
        ProfileValidator validator = new ProfileValidator();
        String password = request.getParameter("oldPassword");
        try {
            UserInfo newUser = validator.validate(request);
            UserInfo oldUser = (UserInfo) request.getSession().getAttribute("authorizedUser");
            String path = request.getServletContext().getResource("")
                    .getPath();
            String pathTemp = path + request.getServletContext()
                    .getInitParameter("images.dir") + "/";
            String pathImage = fileService.createDirAndWriteToFile(pathTemp, request.getPart("image"));
            newUser.setImage(pathImage);
            service.updateUser(newUser, oldUser.getIdEntity(), password);
            logger.info(String.format("user %d  update profile", oldUser.getIdEntity()));
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
