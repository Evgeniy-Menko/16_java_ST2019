package by.menko.finalproject.controller.action.forallaction;

import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.exception.PersonalException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrPageCommand extends Command {
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }
}