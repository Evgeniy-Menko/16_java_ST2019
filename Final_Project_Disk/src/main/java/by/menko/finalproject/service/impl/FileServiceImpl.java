package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.FileService;
import by.menko.finalproject.service.exception.ServicePersonalException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileServiceImpl extends ServiceImpl implements FileService {
    private static Logger logger = LogManager.getLogger();
    private static final String NO_IMAGE = "images/no.png";
    private static final String PATH = "images/";

    @Override
    public String createDirAndWriteToFile(final String pathTemp, final Part filePart, boolean isChange) throws PersonalException, ServicePersonalException {

        String fileName = filePart.getSubmittedFileName();
        String formatImage = fileName.substring(fileName.lastIndexOf('.') + 1);
        boolean flag = "jpg".equals(formatImage) || "png".equals(formatImage) || "jpeg".equals(formatImage) || "gif".equals(formatImage);
        if (fileName.isEmpty() && isChange) {
            return "";
        } else if (fileName.isEmpty()) {
            return NO_IMAGE;
        } else if (!flag) {
            String message = String.format("Error format image %s", fileName);
            logger.debug(message);
            throw new ServicePersonalException("errorFormatImage");
        }
        try {
            File uploadDir = new File(pathTemp);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            File f = new File(pathTemp, fileName);
            BufferedImage image = ImageIO.read(filePart.getInputStream());

            String format = fileName.substring(fileName.lastIndexOf('.') + 1);
            ImageIO.write(image, format, f);
            return PATH + fileName;
        } catch (IOException e) {
            throw new PersonalException(e);
        }
    }
}
