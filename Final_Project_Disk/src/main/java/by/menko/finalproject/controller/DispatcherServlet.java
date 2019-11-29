package by.menko.finalproject.controller;


import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.controller.action.CommandManager;
import by.menko.finalproject.controller.action.CommandManagerFactory;
import by.menko.finalproject.dao.impl.TransactionFactoryImpl;
import by.menko.finalproject.dao.pool.ConnectionPool;
import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.ServiceFactory;
import by.menko.finalproject.service.impl.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    public void init() {
        try {
            ConnectionPool.getInstance();
        } catch (PersonalException e) {
            destroy();
        }
    }

    public ServiceFactory getFactory() throws PersonalException {
        return new ServiceFactoryImpl(new TransactionFactoryImpl());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Command command = (Command) request.getAttribute("action");
        try {
            CommandManager commandManager = CommandManagerFactory.getManager(getFactory());
            commandManager.execute(command, request, response);
            commandManager.close();

        } catch (PersonalException e) {
            e.printStackTrace();
        }
    }
}
