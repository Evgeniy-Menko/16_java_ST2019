package by.menko.present.controller.command;

import by.menko.present.controller.Controller;

import by.menko.present.service.factory.SweetsFactory;
import by.menko.present.service.parser.StringParser;
import by.menko.present.service.validate.Validator;

import java.util.Scanner;

/**
 * @author Evgeniy Menko
 */
public class UpdateSweetsCommand implements Command {
    /**
     * Update sweet by sweet's id.
     */
    @Override
    public void execute() {
        Scanner scan = new Controller().getScan();
        System.out.println("Enter name customer.");
        String nameCustomer = scan.nextLine();


        if (DAO.getAllPresents().containsKey(nameCustomer)) {
            DAO.setNameCustomer(nameCustomer);
            System.out.println("Enter sweets's id.");
            String id = scan.nextLine();
            System.out.println();

            if (new Validator().isValidatorNumber(id)
                    && DAO.getAllPresents().get(nameCustomer)
                    .getSweets().containsKey(Integer.valueOf(id))) {

                System.out.println("Enter Value (for example\n"
                        + ":name,weight(max 500),"
                        + "sugar(max 20%),cost,another parametr:"
                        + "(filling for sweets, "
                        + "view for cookie, flavor for marshmallows))");

                String values = DAO.getAllPresents()
                        .get(nameCustomer).getSweets()
                        .get(Integer.valueOf(id)).getType()
                        + "," + scan.nextLine();

                if (new Validator().isValidatorSweets(new StringParser()
                        .parserObjects(values))) {
                    DAO.addSweets(
                            new SweetsFactory()
                                    .getSweets(new StringParser()
                                            .parserObjects(values)));
                    System.out.println("Sweet is updated.");
                    LOG.info("Sweet is updated.");
                } else {
                    System.out.println("Incorrect values.");
                }
            } else {
                System.out.println("Incorrect id.");
            }
        } else {
            System.out.println("Present with this "
                    + "customers's name doesn't exist.");
        }
    }
}

