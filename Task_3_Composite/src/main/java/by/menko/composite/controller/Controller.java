package by.menko.composite.controller;


import by.menko.composite.controller.command.Command;
import by.menko.composite.controller.command.CommandFactory;
import by.menko.composite.view.MessageManager;

import java.util.Scanner;

public class Controller {
    private MessageManager bundle = MessageManager.EN;
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
     * Controller command.
     */
    public void startApp() {
        String request = null;
        while (!"7".equals(request)) {
            Command.logger.debug(bundle.getMessage("menu"));
            request = scan.nextLine();

            if ("1".equals(request)) {
                Command command = CommandFactory.getInstance()
                        .getCommand(request);
                command.execute(request);
            } else if ("7".equals(request)) {
                Command.logger.debug(bundle.getMessage("stopApp"));
                scan.close();
            } else {
                Command.logger.debug(bundle.getMessage("incorrect"));
            }

        }
    }
}
