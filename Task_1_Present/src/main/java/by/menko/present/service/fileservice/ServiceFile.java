package by.menko.present.service.fileservice;


import by.menko.present.entity.Presents;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Map;

/**
 * @author Evgeniy Menko
 */
public class ServiceFile {
    /**
     * Read the file.
     *
     * @param nameFile file's name.
     *
     * @return
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    public String fileReader(final String nameFile)
            throws IOException, FileNotFoundException {
        String result = null;
        StringBuilder string = new StringBuilder();
        FileInputStream fileInputStream =
                new FileInputStream("data\\" + nameFile + ".txt");
        int i;
        while ((i = fileInputStream.read()) != -1) {
            string.append((char) i);
        }
        result = string.toString();
        return result;
    }

    /**
     * Write to the file.
     *
     * @param nameFile file's name.
     * @param storage  storage for recording.
     *
     * @throws IOException
     */
    public void fileWriter(final String nameFile,
                           final Map<String, Presents> storage)
            throws IOException {
        try (FileOutputStream fileOutputStream =
                     new FileOutputStream("data\\" + nameFile + ".txt")) {
            for (Map.Entry entry : storage.entrySet()) {
                fileOutputStream.write(entry.getValue().toString().getBytes());
            }
        }
    }
}
