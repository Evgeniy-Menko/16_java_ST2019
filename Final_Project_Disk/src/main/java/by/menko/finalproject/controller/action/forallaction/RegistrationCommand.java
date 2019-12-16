package by.menko.finalproject.controller.action.forallaction;


import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;

import by.menko.finalproject.service.FileService;
import by.menko.finalproject.service.UserService;
import by.menko.finalproject.service.validator.RegistrValidator;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class RegistrationCommand extends ForAllAction {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        RegistrValidator validator = new RegistrValidator();
        UserService service = factory.createService(TypeServiceAndDao.USER);
        FileService fileService = factory.createService(TypeServiceAndDao.FILE);
        try {
            UserInfo user = validator.validate(request);
            String path = request.getServletContext().getResource("")
                    .getPath();
            String pathTemp = path + request.getServletContext()
                    .getInitParameter("images.dir") + "/";
            String pathImage = fileService.createDirAndWriteToFile(pathTemp, request.getPart("image"));
            user.setImage(pathImage);
            user = service.registrUser(user);
            request.getSession().setAttribute("authorizedUser", user);
        } catch (ServicePersonalException | IOException e) {
            Map<String, String> message = new HashMap<>();
            message.put(e.getMessage(), e.getMessage());
            String json = new Gson().toJson(message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}
