package by.menko.finalproject.service;

import by.menko.finalproject.dao.exception.PersonalException;

import javax.servlet.http.Part;

public interface FileService extends Service {
    String createDirAndWriteToFile(final String pathTemp,
                                   final Part filePart) throws PersonalException;
}
