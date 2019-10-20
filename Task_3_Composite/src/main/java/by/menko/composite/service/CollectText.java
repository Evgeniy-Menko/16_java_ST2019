package by.menko.composite.service;

import by.menko.composite.dal.Repository;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.repository.StorageRepository;
import by.menko.composite.service.file.ServiceFile;

import java.io.IOException;

public class CollectText {
    /**
     * Collect text and save to the file.
     *
     * @return String.
     *
     * @throws NotInitializationException .
     * @throws IOException                .
     */
    public String collect() throws NotInitializationException, IOException {
        ServiceFile service = new ServiceFile();
        Repository repository = new StorageRepository();
        String response = repository.getComponent().collect();
        service.fileWriter(response);
        return response;
    }
}
