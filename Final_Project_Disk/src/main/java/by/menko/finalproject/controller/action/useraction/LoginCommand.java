package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.entity.User;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand extends Command {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, IOException {
        String login = request.getParameter("email");
        String password = request.getParameter("password");

        if (login != null && password != null) {
            UserService service = factory.createService(TypeServiceAndDao.USER);
            User user = service.finUserByEmail(login, password);
            request.getSession().setAttribute("authorizedUser", user);
            response.sendRedirect("/Panda-Disk/home.html");

            // return new Forward("/index.jsp");
        } else {
            request.setAttribute("message", "Имя пользователя или пароль не опознанны");
        }

    }
}
