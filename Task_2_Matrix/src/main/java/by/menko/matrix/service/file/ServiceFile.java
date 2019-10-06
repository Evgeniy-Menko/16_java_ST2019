package by.menko.matrix.service.file;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceFile {

    /**
     * File reader.
     *
     * @param nameFile .
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
}
