package by.menko.finalproject.controller.action.forallaction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.entity.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LogoutCommand extends ForAllAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {
        UserInfo user = (UserInfo) request.getSession().getAttribute("authorizedUser");
        String message = String.format("user \"%s\" is logged out", user.getIdEntity());
        logger.info(message);
        request.getSession(false).invalidate();
        response.sendRedirect(request.getContextPath() + ConstantsPath.HOME);
    }
}
