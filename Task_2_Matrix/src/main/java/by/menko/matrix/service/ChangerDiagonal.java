package by.menko.matrix.service;

import by.menko.matrix.controller.Controller;
import by.menko.matrix.dal.repositoriy.Repository;
import by.menko.matrix.dal.specification.ExecutorSpecification;
import by.menko.matrix.dal.specification.LockSpecification;
import by.menko.matrix.dal.specification.MyThreadSpecification;
import by.menko.matrix.dal.specification.SemaphoreSpecification;
import by.menko.matrix.service.parser.ParserString;
import by.menko.matrix.service.validate.Validator;
import by.menko.matrix.view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangerDiagonal {
    /**
     * Object scanner.
     */
    private Scanner scan = new Controller().getScan();
    /**
     * Object repository.
     */
    private Repository repository = new Repository();
    /**
     * Diagonal values.
     */
    private List<Integer> valuesDiagonal = new ArrayList<>();

    /**
     * Reads the diagonal values from the
     * console and calls the desired specification.
     *
     * @param action for specification.
     *
     * @return response.
     *
     * @throws InterruptedException .
     */
    public String changeDiagonal(final String action)
            throws InterruptedException {
        System.out.println("Enter values diagonal: ");
        String values = scan.nextLine();

        String[] arrayValues = new ParserString()
                .parserString(values);
        if (new Validator().validateValuesDiagonal(
                arrayValues)) {
            for (int i = 0; i < arrayValues.length; i++) {
                valuesDiagonal.add(Integer.parseInt(arrayValues[i]));
            }
            repository.addValuesDiagonal(valuesDiagonal);
        } else {
            return "Incorrect values or storage is empty.";
        }
        int[][] matrix;
        switch (action) {
            case "2":
                matrix = repository.query(new LockSpecification());
                break;
            case "3":
                matrix = repository.query(new ExecutorSpecification());
                break;
            case "4":
                matrix = repository.query(new SemaphoreSpecification());
                break;
            case "5":
                matrix = repository.query(new MyThreadSpecification());
                break;
            default:
                return "Incorrect action.";
        }
        new Menu().printMatrix(matrix);
        return "Diagonal changed.";
    }
}
