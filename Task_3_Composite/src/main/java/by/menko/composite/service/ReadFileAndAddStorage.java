package by.menko.composite.service;


import by.menko.composite.dal.repository.StorageRepository;
import by.menko.composite.service.file.ServiceFile;
import by.menko.composite.service.parser.ManagerChain;


import java.io.IOException;

public class ReadFileAndAddStorage {
    /**
     * Read file and parse text on components.Add components to the storage.
     *
     * @param nameFile .
     *
     * @return String.
     *
     * @throws IOException .
     */
    public String readAndAddToStorage(final String nameFile)
            throws IOException {

        ServiceFile serviceFile = new ServiceFile();
        StorageRepository repository = new StorageRepository();
        ManagerChain managerChain = new ManagerChain();

        String text = serviceFile.fileReader(nameFile);
        repository.addComponent(managerChain.getParser().dispense(text));
        return "fileAdded";
    }
}
