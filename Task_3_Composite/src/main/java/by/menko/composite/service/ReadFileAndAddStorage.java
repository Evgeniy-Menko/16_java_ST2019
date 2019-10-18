package by.menko.composite.service;

import by.menko.composite.controller.Controller;
import by.menko.composite.dal.repository.StorageRepository;
import by.menko.composite.service.file.ServiceFile;
import by.menko.composite.service.parser.ManagerChain;


import java.io.IOException;

public class ReadFileAndAddStorage {

    public String readAndAddToStorage() throws IOException {

        ServiceFile serviceFile = new ServiceFile();
        StorageRepository repository = new StorageRepository();
        ManagerChain managerChain = new ManagerChain();

        String nameFile = new Controller().getScan().nextLine();
        String text = serviceFile.fileReader(nameFile);
        repository.addComponent(managerChain.getParser().dispense(text));
        return "fileAdded";
    }
}
