package by.menko.finalproject.service;

import by.menko.finalproject.entity.Complaint;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.dao.exception.PersonalException;

import java.util.List;
import java.util.Map;

public interface ComplaintService extends Service {
    /**
     * Added complaint.
     * @param idDisk id disk.
     * @param idUser id user.
     * @param complaint text complaint.
     * @param idUserWasComplaint id user was complaint.
     * @throws PersonalException sql exception.
     */
    void addComplaint(final String idDisk, final String idUser,
                      final String complaint, final Integer idUserWasComplaint)
            throws PersonalException;

    /**
     * Get all complaints for admin.
     * @return return map where key is user and value is complaint.
     * @throws PersonalException   sql exception.
     */
    Map<UserInfo, Complaint> getAllComplaints() throws PersonalException;

    /**
     * Delete complaint by id.
     * @param idComplaint id complaint.
     * @throws PersonalException sql exception.
     */
    void deleteComplaint(final String idComplaint) throws PersonalException;

    /**
     * get disk with complaint by id disk and id user.
     * @param complaintMap map with user and complaint.
     * @return list with disks.
     * @throws PersonalException sql exception.
     */
    List<Disk> getDiskWithComplaint(final Map<UserInfo, Complaint> complaintMap) throws PersonalException;

    /**
     * Blocked announcement by id disk.
     * @param idDisk id disk.
     * @throws PersonalException sql exception.
     */
    void blockAnnouncement(final String idDisk) throws PersonalException;

    /**
     * Unlocked announcement by id disk.
     * @param idDisk id disk.
     * @throws PersonalException sql exception.
     */
    void unlockAnnouncement(final String idDisk) throws PersonalException;
}
