package by.menko.matrix.service;

import by.menko.matrix.controller.Controller;
import by.menko.matrix.dal.repositoriy.Repository;
import by.menko.matrix.service.file.ServiceFile;
import by.menko.matrix.service.parser.ParserString;
import by.menko.matrix.service.validate.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    private Scanner scan = new Controller().getScan();
    private ServiceFile serviceFile = new ServiceFile();
    private ParserString parserString = new ParserString();
    private Repository repository = new Repository();
    private List<String> matrix = new ArrayList<>();

    public void readFileAndAdd() throws IOException {
        System.out.println("Enter directory file and file's name(example: data//File.txt): ");
        String nameFile = scan.nextLine();

        matrix = parserString.parseToList(serviceFile.fileReader(nameFile));
        if (new Validator().validateMatrix(matrix)) {
            repository.addMatrix(matrix);
            System.out.println("File read and matrix added in storage.");
        } else {
            return;
        }
    }
}
