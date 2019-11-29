package by.menko.finalproject.controller.action;

import by.menko.finalproject.service.ServiceFactory;


public class CommandManagerFactory {
    public static CommandManager getManager(ServiceFactory factory) {
        return new CommandManagerImpl(factory);
    }
}
