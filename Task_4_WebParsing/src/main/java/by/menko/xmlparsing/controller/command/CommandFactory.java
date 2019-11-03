package by.menko.xmlparsing.controller.command;

import by.menko.xmlparsing.controller.command.impl.HomeCommand;
import by.menko.xmlparsing.controller.command.impl.ResultParseCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandFactory {
    /**
     * Object class CommandFactory.
     */
    private static CommandFactory commandFactory;

    private CommandFactory() {
    }

    /**
     * Pattern Singleton.
     *
     * @return object CommandFactory.
     */
    public static CommandFactory getInstance() {
        if (commandFactory == null) {
            commandFactory = new CommandFactory();
        }
        return commandFactory;
    }

    /**
     * Map with all command.
     */
    private static Map<String, Command> commandMap =
            new HashMap<>();

    static {
        commandMap.put("result", new ResultParseCommand());
        commandMap.put("home", new HomeCommand());


    }

    /**
     * @param action entered command.
     *
     * @return object class for this command.
     */
    public Command getCommand(final String action) {
        return commandMap.get(action);
    }
}
