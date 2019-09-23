package by.menko.present.controller.command;

import by.menko.present.controller.Controller;
import by.menko.present.entity.Presents;
import by.menko.present.service.validate.Validator;

import java.util.Map;
import java.util.Scanner;

/**
 * @author Evgeniy Menko
 */
public class AddPresentCommand implements Command {
    /**
     * This method added Present for storage.
     */
    @Override
    public void execute() {
        Scanner scan = new Controller().getScan();
        Validator validator = new Validator();
        System.out.println("Enter name customer.");
        String nameCustomer = scan.nextLine();

        Map<String, Presents> storage = DAO.getAllPresents();
        boolean key = storage.containsKey(nameCustomer);
        if (validator.isValidatorPresents(nameCustomer) && !key) {
            DAO.addPresents(new Presents(nameCustomer));
            System.out.println("Present is added.");
            LOG.info("Present is added.");
        } else {
            System.out.println("Incorrect customer's name or"
                    + " present with this customers's name don't exists.");
        }
    }

}

