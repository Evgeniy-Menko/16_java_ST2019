package by.menko.finalproject.service;

import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.exception.ServicePersonalException;

import javax.servlet.http.Part;

public interface FileService extends Service {
    String createDirAndWriteToFile(final String pathTemp,
                                   final Part filePart, final boolean isChange)
            throws PersonalException, ServicePersonalException;
}
