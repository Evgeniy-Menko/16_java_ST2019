package by.menko.present.controller;

import by.menko.present.controller.command.Command;
import by.menko.present.controller.command.CommandFactory;
import by.menko.present.service.validate.Validator;
import by.menko.present.view.Menu;

import java.util.Scanner;

/**
 * @author Evgeniy Menko
 */
public class Controller {
    /**
     * First index a command.
     */
    private final int firstAction = 1;
    /**
     * Last index a command.
     */
    private final int lastAction = 11;
    /**
     * Entered command.
     */
    private String action;
    /**
     * Object Scanner.
     */
    private Scanner scan = new Scanner(System.in);

    /**
     * Getter for Scanner.
     *
     * @return object scanner.
     */
    public Scanner getScan() {
        return scan;
    }


    /**
     * Start app.
     */
    public void startApp() {
        Menu menu = new Menu();
        menu.printMenu();
        while (!"12".equals(action)) {
            System.out.println("Enter a command.");
            action = scan.nextLine();
            boolean validNumber = new Validator().isValidatorNumber(action);
            if (validNumber && Integer.valueOf(action) >= firstAction
                    && Integer.valueOf(action) <= lastAction) {
                Command command = CommandFactory.getInstance()
                        .getCommand(action);
                command.execute();
            } else if ("12".equals(action)) {
                System.out.println("Application is stopped.");
            } else {
                System.out.println("Incorrect command.");
            }
        }
    }
}
