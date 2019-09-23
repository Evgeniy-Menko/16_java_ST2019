package by.menko.present.controller.command;

import by.menko.present.controller.Controller;

import by.menko.present.entity.Sweets;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Evgeniy Menko
 */
public class CalculateWeightCommand implements Command {
    /**
     * This method calculate weight of all sweets.
     */
    @Override
    public void execute() {
        Scanner scan = new Controller().getScan();
        System.out.println("Enter customer's name");
        String nameCustomer = scan.nextLine();


        if (DAO.getAllPresents().containsKey(nameCustomer)) {
            Map<Integer, Sweets> sweets = new HashMap<Integer, Sweets>();
            sweets = DAO.getAllPresents().get(nameCustomer).getSweets();

            if (!sweets.isEmpty()) {
                int allWeight = 0;

                for (Map.Entry<Integer, Sweets> entry : sweets.entrySet()) {
                    allWeight += entry.getValue().getWeight();
                }
                System.out.println("The weight "
                        + "of all sweets = " + allWeight);
            } else {
                System.out.println("There are not sweets in this present.");
            }
        } else {
            System.out.println("Present with this "
                    + "customers's name doesn't exist.");
        }
    }
}

