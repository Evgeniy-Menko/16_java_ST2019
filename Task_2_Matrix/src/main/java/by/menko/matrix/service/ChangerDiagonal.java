package by.menko.matrix.service;

import by.menko.matrix.controller.Controller;
import by.menko.matrix.dal.MatrixRepository;
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
    private Scanner scan = new Controller().getScan();
    private Repository repository = new Repository();
    private List<Integer> valuesDiagonal = new ArrayList<>();

    public void changeDiagonal(final String command)
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
            System.out.println("incorrect values");
            return;
        }
        int[][] matrix;
        switch (command) {
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
                return;
        }
        new Menu().printMatrix(matrix);
        return;
    }
}
