package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.FileService;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileServiceImpl extends ServiceImpl implements FileService {
    private final static String NO_IMAGE = "images/no.png";
    private final static String PATH = "images/";

    @Override
    public String createDirAndWriteToFile(final String pathTemp, final Part filePart) throws PersonalException {
        String fileName = filePart.getSubmittedFileName();
        if (fileName.isEmpty()) {
            return NO_IMAGE;
        }
        try {
            File uploadDir = new File(pathTemp);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            File f = new File(pathTemp, fileName);
            BufferedImage image = null;

            image = ImageIO.read(filePart.getInputStream());

            String format = fileName.substring(fileName.lastIndexOf('.') + 1);
            ImageIO.write(image, format, f);
            return PATH + fileName;
        } catch (IOException e) {
            throw new PersonalException(e);
        }
    }
}
