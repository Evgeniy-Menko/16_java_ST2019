package by.menko.finalproject.controller.action.forallaction;


import by.menko.finalproject.controller.action.Command;

import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.exception.ServicePersonalException;
import by.menko.finalproject.service.UserService;
import com.google.gson.Gson;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand extends Command {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, IOException {
        String login = request.getParameter("email");
        String password = request.getParameter("password");
        Map<String, String> message = new HashMap<>();
        try {
            if (login != null && password != null) {
                UserService service = factory.createService(TypeServiceAndDao.USER);
                UserInfo user = service.finUserByEmail(login, password);
                request.getSession().setAttribute("authorizedUser", user);
                String referrer = request.getHeader("referer");
                message.put("url", referrer);
                String json = new Gson().toJson(message);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            }
        } catch (ServicePersonalException e) {
            message.put(e.getMessage(), e.getMessage());
            String json = new Gson().toJson(message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }
}
