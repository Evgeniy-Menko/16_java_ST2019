package by.menko.finalproject.controller.action;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.ServiceFactory;

import java.io.IOException;


public abstract class Command {


    protected ServiceFactory factory;


    public void setFactory(final ServiceFactory factory) {
        this.factory = factory;
    }

    public abstract void exec(final HttpServletRequest request, final HttpServletResponse response) throws PersonalException, ServletException, IOException;


}