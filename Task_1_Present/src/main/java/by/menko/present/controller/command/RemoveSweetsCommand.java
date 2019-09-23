package by.menko.present.controller.command;

import by.menko.present.controller.Controller;
import by.menko.present.service.validate.Validator;

import java.util.Scanner;

/**
 * @author Evgeniy Menko
 */
public class RemoveSweetsCommand implements Command {
    /**
     * Remove sweet by sweet's id.
     */
    @Override
    public void execute() {
        Scanner scan = new Controller().getScan();
        System.out.println("Enter customer's name whose"
                + " sweet you want to remove.");
        String nameCustomer = scan.nextLine();
        System.out.println("Enter sweet's id.");
        String id = scan.nextLine();

        if (DAO.getAllPresents().containsKey(nameCustomer)
                && new Validator().isValidatorNumber(id)
                && DAO.getAllPresents().get(nameCustomer)
                .getSweets().containsKey(Integer.valueOf(id))) {
            DAO.removeSweets(Integer.valueOf(id), nameCustomer);
            System.out.println("Sweet is removed.");
            LOG.info("Sweet is removed.");
        } else {
            System.out.println("Present with this "
                    + "customers's name or sweets's id don't exist.");
        }
    }
}
