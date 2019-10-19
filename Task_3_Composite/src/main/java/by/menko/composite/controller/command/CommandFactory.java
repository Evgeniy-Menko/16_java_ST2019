package by.menko.composite.controller.command;

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
        commandMap.put("1", new ReadFileCommand());
        commandMap.put("2", new SortByCountCommand());
        commandMap.put("3", new SortByCountCommand());
        commandMap.put("4", new SortBySymbolCommand());
        commandMap.put("5", new CollectTextCommand());
        commandMap.put("6", new ChangeLanguageCommand());


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
