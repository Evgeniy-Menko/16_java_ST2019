package by.menko.xmlparsing.service.file;

import org.apache.logging.log4j.core.util.IOUtils;

import javax.servlet.http.Part;
import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileService {
    public void createDirAndWriteToFile(String pathTemp, Part filePart) throws IOException {

        File uploadDir = new File(pathTemp);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        // Retrieves
        String fileName = filePart.getSubmittedFileName(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();

        String result = IOUtils.toString(new InputStreamReader(
                fileContent, UTF_8));

        try (FileOutputStream fos = new FileOutputStream(new File(pathTemp, fileName))) {
            // перевод строки в байты
            byte[] buffer = result.getBytes();
            fos.write(buffer, 0, buffer.length);
        }
    }
}
