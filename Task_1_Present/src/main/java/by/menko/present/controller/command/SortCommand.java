package by.menko.present.controller.command;

import by.menko.present.controller.Controller;

import by.menko.present.dal.spetification.*;
import by.menko.present.dal.spetification.impl.Specification;
import by.menko.present.entity.Sweets;
import by.menko.present.service.validate.Validator;

import java.util.List;
import java.util.Scanner;

/**
 * @author Evgeniy Menko
 */
public class SortCommand implements Command {
    /**
     * Index id.
     */
    private final int indexId = 4;

    /**
     * Print List<Sweets> by choose sort.
     */
    @Override
    public void execute() {
        Scanner scan = new Controller().getScan();
        System.out.println("Choose sorting:\n"
                + "1) Sort by name.\n"
                + "2) Sort by sugar.\n"
                + "3) Sort by weight.\n"
                + "4) Sort by cost.\n"
                + "5) Sort by name and cost.\n"
                + "6) Sort by sugar and weight.\n");
        String typeSort = scan.nextLine();
        if (new Validator().isValidatorNumber(typeSort)) {
            Specification specification = null;
            switch (typeSort) {
                case "1":
                    specification = new SortByNameSpecification();
                    break;
                case "2":
                    specification = new SortBySugarSpecification();
                    break;
                case "3":
                    specification = new SortByWeightSpecification();
                    break;
                case "4":
                    specification = new SortByCostSpecification();
                    break;
                case "5":
                    specification = new SortByNameAndCostSpecification();
                    break;
                case "6":
                    specification = new SortBySugarAndWeightSpecification();
                    break;
                default:
                    System.out.println("Incorrect type of sorting.");
                    return;
            }
            List<Sweets> result = DAO.query(specification);
            if (!result.isEmpty()) {

                for (int i = 0; i < result.size(); i++) {
                    System.out.println(result.get(i)
                            .print().substring(indexId));
                }
            } else {
                System.out.println("Sweets are not found.");
            }
        } else {
            System.out.println("Incorrect type of sorting.");
        }
    }
}
