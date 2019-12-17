package by.menko.finalproject.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.menko.finalproject.dao.exception.PersonalException;

import java.io.IOException;


public interface CommandManager {
    void execute(final Command command, final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException;

    void close();
}
