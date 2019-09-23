package by.menko.present.controller.command;

import by.menko.present.controller.Controller;
import by.menko.present.service.fileservice.ServiceFile;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Evgeniy Menko
 */
public class WriteFileCommand implements Command {
    /**
     * Write to the file.
     */
    @Override
    public void execute() {

        ServiceFile serviceFile = new ServiceFile();
        try {
            Scanner scan = new Controller().getScan();
            System.out.print("Enter file's name.");
            String nameFile = scan.nextLine();
            serviceFile.fileWriter(nameFile, DAO.getAllPresents());
            System.out.println("Record is successful.");
            LOG.info("Record is successful.");
        } catch (IOException e) {
            System.out.print("Write to file failed.");
            LOG.error("Write to file failed.", e);
        }
    }
}
