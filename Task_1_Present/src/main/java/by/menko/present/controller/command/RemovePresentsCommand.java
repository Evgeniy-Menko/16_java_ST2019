package by.menko.present.controller.command;

import by.menko.present.controller.Controller;


import java.util.Scanner;

/**
 * @author Evgeniy Menko
 */
public class RemovePresentsCommand implements Command {
    /**
     * remove present by customer's name.
     */
    @Override
    public void execute() {
        Scanner scan = new Controller().getScan();
        System.out.println("Enter customer's name whose"
                + " present you want to remove.");
        String nameCustomer = scan.nextLine();

        if (DAO.getAllPresents().containsKey(nameCustomer)) {
            DAO.removePresents(nameCustomer);
            System.out.println("Present is removed.");
            LOG.info("Present is removed.");
        } else {
            System.out.println("Present with this "
                    + "customers's name doesn't exist.");
        }
    }
}
