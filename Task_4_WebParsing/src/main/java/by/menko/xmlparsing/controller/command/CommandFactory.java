package by.menko.xmlparsing.controller.command;

import by.menko.xmlparsing.controller.command.impl.HomeCommand;
import by.menko.xmlparsing.controller.command.impl.ResultParseCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static CommandFactory commandFactory;

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        if (commandFactory == null) {
            synchronized (CommandFactory.class) {

                commandFactory = new CommandFactory();
            }
        }
        return commandFactory;
    }

    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        commandMap.put("result", new ResultParseCommand());
        commandMap.put("home", new HomeCommand());

    }

    public Command getCommand(String action) {
        return commandMap.get(action);

    }

}
