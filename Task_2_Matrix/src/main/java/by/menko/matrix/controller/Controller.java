package by.menko.matrix.controller;

import by.menko.matrix.controller.command.Command;
import by.menko.matrix.controller.command.CommandFactory;
import by.menko.matrix.view.Menu;

import java.util.Scanner;

public class Controller {
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
     * Controller command.
     */
    public void startApp() {
        Menu menu = new Menu();
        menu.printMenu();
        String action = null;
        while (!"6".equals(action)) {
            System.out.println("Enter a command.");
            action = scan.nextLine();

            if ("1".equals(action)) {
                Command command = CommandFactory.getInstance()
                        .getCommand(action);
                command.execute(action);
            } else if ("2".equals(action) || "3".equals(action)
                    || "4".equals(action) || "5".equals(action)) {
                Command command1 = CommandFactory.getInstance()
                        .getCommand("2");
                command1.execute(action);
            } else if ("6".equals(action)) {
                System.out.println("Application is stopped.");
                scan.close();
            } else {
                System.out.println("Incorrect command.");
            }
        }
    }
}
