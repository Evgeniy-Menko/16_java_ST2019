package by.menko.finalproject.service;

import by.menko.finalproject.entity.Complaint;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.exception.PersonalException;

import java.util.List;
import java.util.Map;

public interface ComplaintService extends Service {
    void addComplaint(String idDisk, String idUser, String complaint, Integer idUserWasComplaint) throws PersonalException;

    Map<UserInfo, Complaint> getAllComplaints() throws PersonalException;

    void deleteComplaint(String idComplaint) throws PersonalException;

    List<Disk> getDiskWithComplaint(Map<UserInfo, Complaint> complaintMap) throws PersonalException;
}
