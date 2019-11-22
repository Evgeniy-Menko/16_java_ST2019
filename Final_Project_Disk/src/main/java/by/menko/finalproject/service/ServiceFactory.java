package by.menko.finalproject.service;

import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.exception.PersonalException;

public interface ServiceFactory {
    <T extends Service> T createService(TypeServiceAndDao key) throws PersonalException;

    void close();
}
