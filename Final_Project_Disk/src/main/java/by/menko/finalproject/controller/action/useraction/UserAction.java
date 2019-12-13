package by.menko.finalproject.controller.action.useraction;

import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.exception.PersonalException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class UserAction extends Command {
    abstract public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException;
}


