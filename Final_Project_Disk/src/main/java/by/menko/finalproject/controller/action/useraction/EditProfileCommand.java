package by.menko.finalproject.controller.action.useraction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProfileCommand extends UserAction {
    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        UserService service = factory.createService(TypeServiceAndDao.USER);
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("authorizedUser");
        userInfo = service.getUser(userInfo.getIdEntity());
        request.setAttribute("userInfo", userInfo);
        request.getRequestDispatcher(ConstantsPath.EDIT_PROFILE_PAGE).forward(request, response);
    }
}
