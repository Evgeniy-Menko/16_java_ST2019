package by.menko.matrix.service;

import by.menko.matrix.controller.Controller;
import by.menko.matrix.dal.repositoriy.Repository;
import by.menko.matrix.service.file.ServiceFile;
import by.menko.matrix.service.parser.ParserString;
import by.menko.matrix.service.validate.Validator;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    /**
     * Object scanner.
     */
    private Scanner scan = new Controller().getScan();
    /**
     * Object ServiceFile.
     */
    private ServiceFile serviceFile = new ServiceFile();
    /**
     * Object ParseString.
     */
    private ParserString parserString = new ParserString();
    /**
     * Object Repository.
     */
    private Repository repository = new Repository();

    /**
     * Read file and added to the storage.
     * @return string response.
     * @throws IOException .
     */
    public String readFileAndAdd() throws IOException {
        System.out.println("Enter directory file and file's name"
                + "(example: data//File.txt): ");
        String nameFile = scan.nextLine();

        List<String> valuesMatrix = parserString
                .parseToList(serviceFile.fileReader(nameFile));
        if (new Validator().validateMatrix(valuesMatrix)) {
            repository.addMatrix(valuesMatrix);
            return "File is read and matrix is added in storage.";
        } else {
            return "Values failed validation.";
        }
    }
}
