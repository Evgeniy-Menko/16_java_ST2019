package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileCommand extends UserAction {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        UserService service = factory.createService(TypeServiceAndDao.USER);
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("authorizedUser");
        userInfo = service.getUser(userInfo.getIdEntity());
        request.setAttribute("userInfo", userInfo);
        request.getRequestDispatcher(ConstantsPath.PROFILE_PAGE).forward(request, response);

    }
}
