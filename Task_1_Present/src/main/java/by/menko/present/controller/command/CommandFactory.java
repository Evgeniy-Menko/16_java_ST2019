package by.menko.present.controller.command;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Evgeniy Menko
 */
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
            new HashMap<String, Command>();

    static {
        commandMap.put("1", new ReadFileCommand());
        commandMap.put("2", new WriteFileCommand());
        commandMap.put("3", new AddPresentCommand());
        commandMap.put("4", new AddSweetsCommand());
        commandMap.put("5", new CalculateWeightCommand());
        commandMap.put("6", new UpdateSweetsCommand());
        commandMap.put("7", new RemovePresentsCommand());
        commandMap.put("8", new RemoveSweetsCommand());
        commandMap.put("9", new ShowAllCommand());
        commandMap.put("10", new FindbySugarCommand());
        commandMap.put("11", new SortCommand());
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
