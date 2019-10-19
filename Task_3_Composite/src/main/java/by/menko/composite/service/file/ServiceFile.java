package by.menko.composite.service.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceFile {

    /**
     * File reader.
     *
     * @param nameFile file's name.
     *
     * @return string values.
     *
     * @throws IOException .
     */
    public String fileReader(final String nameFile) throws IOException {
        String result;
        try (Stream<String> stream = Files.lines(Paths.get(nameFile))) {
            result = stream.collect(Collectors.joining("\n"));
        }
        return result;
    }

    /**
     * Write to the file.
     *
     * @param text text.
     *
     * @throws IOException .
     */
    public void fileWriter(final String text) throws IOException {
        try (FileOutputStream fileOutputStream =
                     new FileOutputStream("data//result.txt")) {
            fileOutputStream.write(text.getBytes());

        }
    }

}
