package by.menko.finalproject.controller.action.forallaction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.exception.ServicePersonalException;

import by.menko.finalproject.service.FileService;
import by.menko.finalproject.service.UserService;
import by.menko.finalproject.service.validator.RegistrValidator;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class RegistrationCommand extends ForAllAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        UserService service = factory.createService(TypeServiceAndDao.USER);
        FileService fileService = factory.createService(TypeServiceAndDao.FILE);
        Map<String, String> messages = new HashMap<>();
        try {
            UserInfo user = getUser(request);
            String repeatPassword = request.getParameter("password2");
            String path = request.getServletContext().getResource("")
                    .getPath();
            String pathTemp = path + request.getServletContext()
                    .getInitParameter("images.dir") + "/";
            String pathImage = fileService.createDirAndWriteToFile(pathTemp, request.getPart("image"), false);
            user.setImage(pathImage);
            user = service.registrUser(user, repeatPassword);
            request.getSession().setAttribute("authorizedUser", user);
            String message = String.format("user \"%d\" is registered in from %s (%s:%s)", user.getIdEntity(), request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort());
            logger.info(message);
            messages.put("url", request.getContextPath() + ConstantsPath.MY_PROFILE);
            String json = new Gson().toJson(message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (ServicePersonalException | IOException e) {
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
     * @return the user entity.
     */
    private UserInfo getUser(final HttpServletRequest request) throws IOException, ServletException {
        Part image = request.getPart("image");
        String imageName = image.getSubmittedFileName();
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserInfo user = new UserInfo();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setImage(imageName);
        user.setPhone(phone);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
