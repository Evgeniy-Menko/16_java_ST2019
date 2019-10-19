package by.menko.composite.controller;


import by.menko.composite.controller.command.Command;
import by.menko.composite.controller.command.CommandFactory;
import by.menko.composite.view.MessageManager;


import java.util.Scanner;

public class Controller {
    private static MessageManager bundle = MessageManager.EN;
    /**
     * Object Scanner.
     */
    private Scanner scan = new Scanner(System.in);

    /**
     * Getter for scan.
     *
     * @return object Scanner.
     */
    public Scanner getScan() {
        return scan;
    }

    /**
     * Getter for bundle.
     *
     * @return object bundle.
     */
    public MessageManager getBundle() {
        return bundle;
    }

    /**
     * Setter for bundle.
     *
     * @param resourceBundle .
     */
    public void setBundle(final MessageManager resourceBundle) {
        this.bundle = resourceBundle;
    }

    /**
     * Controller command.
     */
    public void startApp() {
        String request = null;
        while (!"7".equals(request)) {
            Command.logger.debug(bundle.getMessage("menu"));
            request = scan.nextLine();
            switch (request) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                    Command command = CommandFactory.getInstance()
                            .getCommand(request);
                    command.execute(request);
                    break;
                case "7":
                    Command.logger.debug(bundle.getMessage("stopApp"));
                    scan.close();
                    break;
                default:
                    Command.logger.debug(bundle.getMessage("incorrect"));
            }
        }
    }
}

