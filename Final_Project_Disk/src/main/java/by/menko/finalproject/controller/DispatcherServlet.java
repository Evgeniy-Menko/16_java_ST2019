package by.menko.finalproject.controller;


import by.menko.finalproject.controller.action.Command;
import by.menko.finalproject.controller.action.CommandManager;
import by.menko.finalproject.controller.action.CommandManagerFactory;
import by.menko.finalproject.controller.action.forallaction.MenuCommand;
import by.menko.finalproject.controller.constantspath.ConstantsPath;
import by.menko.finalproject.dao.impl.TransactionFactoryImpl;
import by.menko.finalproject.dao.pool.ConnectionPool;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.ServiceFactory;
import by.menko.finalproject.service.impl.ServiceFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class DispatcherServlet extends HttpServlet {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void init() {
        ConnectionPool.getInstance();
    }

    public ServiceFactory getFactory() throws PersonalException {
        return new ServiceFactoryImpl(new TransactionFactoryImpl());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Command command = (Command) request.getAttribute("action");
        try {
            Command menu = new MenuCommand();
            CommandManager commandManager = CommandManagerFactory.getManager(getFactory());
            commandManager.execute(menu, request, response);
            commandManager.execute(command, request, response);
            commandManager.close();
        } catch (PersonalException e) {
            logger.error(String.format("It is impossible to process request %s %s", e.getMessage(), e));
            request.getRequestDispatcher(ConstantsPath.ERROR_PAGE).forward(request, response);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }
}
