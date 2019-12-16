package by.menko.finalproject.controller.action.useraction;

import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.exception.PersonalException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAnnouncementCommand extends UserAction {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
          request.getRequestDispatcher(ConstantsPath.ADD_ANNOUNCEMENT).forward(request,response);
    }
}
