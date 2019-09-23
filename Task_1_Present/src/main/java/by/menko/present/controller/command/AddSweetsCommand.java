package by.menko.present.controller.command;

import by.menko.present.controller.Controller;

import by.menko.present.service.factory.SweetsFactory;
import by.menko.present.service.parser.StringParser;
import by.menko.present.service.validate.Validator;

import java.util.Scanner;

/**
 * @author Evgeniy Menko
 */
public class AddSweetsCommand implements Command {
    /**
     * This method added sweet for storage.
     */
    @Override
    public void execute() {
        Scanner scan = new Controller().getScan();
        System.out.println("Enter customer's name");
        String nameCustomer = scan.nextLine();


        if (DAO.getAllPresents().containsKey(nameCustomer)) {
            DAO.setNameCustomer(nameCustomer);
            System.out.println("Choose:\n"
                    + "1) Chocolate\n"
                    + "2) Cookie\n"
                    + "3) Marshmallows");

            String type = scan.nextLine();

            switch (type) {
                case "1":
                case "2":
                case "3":
                    System.out.println("Enter Value (for example\n"
                            + ":name,weight(max 500),"
                            + "sugar(max 20%),cost,another parametr:"
                            + "(filling for sweets, "
                            + "view for cookie, flavor for marshmallows))");
                    String values = null;
                    if ("1".equals(type)) {
                        values = "chocolate," + scan.nextLine();
                    } else if ("2".equals(type)) {
                        values = "cookie," + scan.nextLine();
                    } else if ("3".equals(type)) {
                        values = "marshmallows," + scan.nextLine();
                    }

                    if (new Validator()
                            .isValidatorSweets(
                                    new StringParser()
                                            .parserObjects(values))) {

                        DAO.addSweets(
                                new SweetsFactory()
                                        .getSweets(new StringParser()
                                                .parserObjects(values)));
                        System.out.println("Sweet is added.");
                        LOG.info("Sweet is added.");
                    } else {
                        System.out.println("Incorrect values.");
                    }
                    break;
                default:
                    System.out.print("Incorrect type's sweets.");
            }

        } else {
            System.out.println("Present with this "
                    + "customers's name doesn't exist.");
        }
    }
}

