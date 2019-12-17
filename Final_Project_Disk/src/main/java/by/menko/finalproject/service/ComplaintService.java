package by.menko.finalproject.service;

import by.menko.finalproject.entity.Complaint;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.dao.exception.PersonalException;

import java.util.List;
import java.util.Map;

public interface ComplaintService extends Service {
    void addComplaint(final String idDisk, final String idUser,
                      final String complaint, final Integer idUserWasComplaint)
            throws PersonalException;

    Map<UserInfo, Complaint> getAllComplaints() throws PersonalException;

    void deleteComplaint(final String idComplaint) throws PersonalException;

    List<Disk> getDiskWithComplaint(final Map<UserInfo, Complaint> complaintMap) throws PersonalException;

    void blockAnnouncement(final String idDisk) throws PersonalException;

    void unlockAnnouncement(final String idDisk) throws PersonalException;
}
