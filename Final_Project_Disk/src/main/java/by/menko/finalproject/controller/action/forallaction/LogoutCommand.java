package by.menko.finalproject.controller.action.forallaction;


import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.exception.PersonalException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import org.apache.log4j.Logger;

public class LogoutCommand extends ForAllAction {


    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        request.getSession(false).invalidate();
        response.sendRedirect(request.getContextPath()+ ConstantsPath.HOME);
    }
}
