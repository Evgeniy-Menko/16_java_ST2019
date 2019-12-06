package by.menko.finalproject.service;

import by.menko.finalproject.exception.PersonalException;

public interface ComplaintService extends Service {
     void addComplaint(String idDisk, String idUser, String complaint, Integer idUserWasComplaint) throws PersonalException;
}
