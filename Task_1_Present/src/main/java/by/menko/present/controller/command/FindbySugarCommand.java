package by.menko.present.controller.command;

import by.menko.present.controller.Controller;
import by.menko.present.dal.spetification.FindBySugarSpecification;
import by.menko.present.entity.Sweets;
import by.menko.present.service.validate.Validator;

import java.util.List;
import java.util.Scanner;

/**
 * @author Evgeniy Menko
 */
public class FindbySugarCommand implements Command {
    /**
     * Index id.
     */
    private final int indexId = 4;

    /**
     * Print list<Sweets> wich find by sugar range.
     */
    @Override
    public void execute() {
        Scanner scan = new Controller().getScan();
        System.out.println("Enter min sugar.");
        String minSugar = scan.nextLine();
        System.out.println("Enter max sugar.");
        String maxSugar = scan.nextLine();
        if (new Validator().isValidatorNumber(minSugar)
                && new Validator().isValidatorNumber(maxSugar)
                && Integer.valueOf(maxSugar)
                > Integer.valueOf(minSugar)) {
            int min = Integer.valueOf(minSugar);
            int max = Integer.valueOf(maxSugar);

            List<Sweets> result = DAO
                    .query(new FindBySugarSpecification(min, max));
            if (!result.isEmpty()) {
                for (int i = 0; i < result.size(); i++) {
                    System.out.println(result.get(i).print()
                            .substring(indexId));
                }
            } else {
                System.out.println("Sweets are not found.");
            }
        } else {
            System.out.println("Incorrect number.");
        }
    }

}

