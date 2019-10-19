package by.menko.composite.service;

import by.menko.composite.dal.Repository;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.repository.StorageRepository;
import by.menko.composite.service.file.ServiceFile;

import java.io.IOException;

public class CollectText {

    public String collect() throws NotInitializationException, IOException {
        ServiceFile service = new ServiceFile();
        Repository repository = new StorageRepository();
        String response = repository.getComponent().operation();
        service.fileWriter(response);
        return response;
    }
}
