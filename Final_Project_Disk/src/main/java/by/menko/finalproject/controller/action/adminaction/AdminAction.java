package by.menko.finalproject.controller.action.adminaction;

import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.dao.exception.PersonalException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AdminAction extends Command {
    public abstract void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException;
}
