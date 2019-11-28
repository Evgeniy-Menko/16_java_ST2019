package by.menko.finalproject.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.menko.finalproject.exception.PersonalException;
import by.menko.finalproject.service.ServiceFactory;

import java.io.IOException;


public class CommandManagerImpl implements CommandManager {
    private ServiceFactory factory;

    public CommandManagerImpl(ServiceFactory factory) {
        this.factory = factory;
    }

    @Override
    public void execute(Command command, HttpServletRequest request, HttpServletResponse response) throws PersonalException, ServletException, IOException {
        command.setFactory(factory);
        command.exec(request, response);
    }

    @Override
    public void close() {
        factory.close();
    }
}
