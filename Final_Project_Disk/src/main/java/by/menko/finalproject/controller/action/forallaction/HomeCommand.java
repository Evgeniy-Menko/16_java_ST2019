package by.menko.finalproject.controller.action.forallaction;


import by.menko.finalproject.controller.constantspath.ConstantsPath;


import by.menko.finalproject.dao.exception.PersonalException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HomeCommand extends ForAllAction {


    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException {

        request.getRequestDispatcher(ConstantsPath.INDEX_PAGE).forward(request, response);

    }
}