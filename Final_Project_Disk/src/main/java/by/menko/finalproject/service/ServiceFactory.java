package by.menko.finalproject.service;

import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;

public interface ServiceFactory {
    <T extends Service> T createService(final TypeServiceAndDao key) throws PersonalException;

    void close();
}
