package by.menko.present.controller.command;

import by.menko.present.controller.Controller;
import by.menko.present.entity.Presents;
import by.menko.present.service.factory.SweetsFactory;
import by.menko.present.service.fileservice.ServiceFile;
import by.menko.present.service.parser.StringParser;
import by.menko.present.service.validate.Validator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Evgeniy Menko
 */
public class ReadFileCommand implements Command {
    /**
     * Read the file and write to storage.
     */
    @Override
    public void execute() {
        try {
            Scanner scan = new Controller().getScan();

            Validator validator = new Validator();
            ServiceFile serviceFile = new ServiceFile();
            StringParser parser = new StringParser();
            List<String> list = new ArrayList<String>();

            System.out.println("Enter file's name.");
            String nameFile = scan.nextLine();
            String string = serviceFile.fileReader(nameFile);
            list = parser.parseString(string);
            for (int i = 0; i < list.size(); i++) {
                if (validator.isValidatorPresents(parser
                        .parserObjects(list.get(i)))) {
                    DAO.setNameCustomer(list.get(i));
                    DAO.addPresents(new Presents(list.get(i)));
                } else if (validator.isValidatorSweets(parser
                        .parserObjects(list.get(i)))) {
                    SweetsFactory factory = new SweetsFactory();
                    DAO.addSweets(factory.getSweets(parser
                            .parserObjects(list.get(i))));
                }
            }
            System.out.println("File is read and "
                    + "objects are added in storage.");
            LOG.info("File is read and objects are added in storage.");
        } catch (FileNotFoundException e) {
            System.out.println("File is not found.");
            LOG.info("File is not found.");
        } catch (
                IOException e) {
            LOG.error(e);
        }
    }
}

