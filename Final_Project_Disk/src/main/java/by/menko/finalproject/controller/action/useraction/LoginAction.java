package by.menko.finalproject.controller.action.useraction;

import by.menko.finalproject.controller.action.Action;
import by.menko.finalproject.entity.User;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException {
        String login = request.getParameter("email");
        String password = request.getParameter("password");
        if (login != null && password != null) {
            UserService service = factory.createService("user");
            User user = service.finUserByEmail(login, password);
            request.getSession().setAttribute("authorizedUser", user);
            return new Forward("/index.jsp");
        } else {
            request.setAttribute("message", "Имя пользователя или пароль не опознанны");
        }
        return null;
    }
}
