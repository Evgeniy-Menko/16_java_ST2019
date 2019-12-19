package by.menko.finalproject.service.impl;

import by.menko.finalproject.dao.ComplaintDao;

import by.menko.finalproject.dao.DiskDao;
import by.menko.finalproject.dao.UserDao;

import by.menko.finalproject.entity.Complaint;
import by.menko.finalproject.entity.Disk;
import by.menko.finalproject.entity.UserInfo;
import by.menko.finalproject.entity.enumtype.TypeServiceAndDao;
import by.menko.finalproject.dao.exception.PersonalException;
import by.menko.finalproject.service.ComplaintService;


import java.util.*;

public class ComplaintServiceImpl extends ServiceImpl implements ComplaintService {

    private static final String REGEX_SENTENCE = "^(?!\\s\\t\\n\\r*$)[A-zА-яЁё0-9,.!@#?:()_\\t\\n\\r ]*$";

    @Override
    public void addComplaint(final String idDisk, final String idUser,
                             final String complaintText, final Integer idUserWasComplaint)
            throws PersonalException {
        ComplaintDao dao = transaction.createDao(TypeServiceAndDao.COMPLAINT);
        Complaint complaint = new Complaint();
        try {
            Integer userId = Integer.parseInt(idUser);
            Integer diskId = Integer.parseInt(idDisk);
            if (complaintText != null && !complaintText.isEmpty() && complaintText.matches(REGEX_SENTENCE)) {
                complaint.setTextComplaint(complaintText);
                complaint.setIdDisk(diskId);
                complaint.setUserIdComplained(userId);
                complaint.setUserWasComplained(idUserWasComplaint);
                dao.create(complaint);
                transaction.commit();
            } else {
                throw new PersonalException("Incorrect complaint text");
            }
        } catch (NumberFormatException | PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }

    @Override
    public Map<UserInfo, Complaint> getAllComplaints() throws PersonalException {
        try {
            ComplaintDao dao = transaction.createDao(TypeServiceAndDao.COMPLAINT);
            List<Complaint> complaintList = dao.readAll();
            UserDao userDao = transaction.createDao(TypeServiceAndDao.USER);
            Optional<UserInfo> user;
            Map<UserInfo, Complaint> mapUserAndComplaint = new HashMap<>();
            for (Complaint complaint : complaintList) {
                user = userDao.read(complaint.getUserWasComplained());
                user.ifPresent(userInfo -> mapUserAndComplaint.put(userInfo, complaint));
            }
            transaction.commit();
            return mapUserAndComplaint;
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }

    public void deleteComplaint(final String idComplaint) throws PersonalException {
        ComplaintDao dao = transaction.createDao(TypeServiceAndDao.COMPLAINT);
        try {
            Integer id = Integer.parseInt(idComplaint);
            dao.delete(id);
            transaction.commit();
        } catch (NumberFormatException | PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }

    @Override
    public List<Disk> getDiskWithComplaint(final Map<UserInfo, Complaint> complaintMap) throws PersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        try {
            List<Disk> diskList = new ArrayList<>();
            Optional<Disk> disk;
            for (Complaint item : complaintMap.values()) {
                disk = dao.readForAdmin(item.getIdDisk());
                disk.ifPresent(diskList::add);
            }
            transaction.commit();
            return diskList;
        } catch (PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }

    @Override
    public void blockAnnouncement(final String idDisk) throws PersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        try {
            Integer diskId = Integer.parseInt(idDisk);
            dao.blocked(diskId);
            transaction.commit();
        } catch (NumberFormatException | PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }

    @Override
    public void unlockAnnouncement(final String idDisk) throws PersonalException {
        DiskDao dao = transaction.createDao(TypeServiceAndDao.DISK);
        try {
            Integer diskId = Integer.parseInt(idDisk);
            dao.unLock(diskId);
            transaction.commit();
        } catch (NumberFormatException | PersonalException e) {
            transaction.rollback();
            throw new PersonalException(e);
        }
    }
}
