package by.menko.finalproject.controller.action;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.ServiceFactory;

import java.io.IOException;



abstract public class Command {


    protected ServiceFactory factory;


    public void setFactory(ServiceFactory factory) {
        this.factory = factory;
    }

    abstract public void exec(HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException;

    
}